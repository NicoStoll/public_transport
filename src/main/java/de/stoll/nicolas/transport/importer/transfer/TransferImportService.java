package de.stoll.nicolas.transport.importer.transfer;

import de.stoll.nicolas.transport.data.Stop;
import de.stoll.nicolas.transport.data.StopRepository;
import de.stoll.nicolas.transport.data.Transfer;
import de.stoll.nicolas.transport.data.TransferRepository;
import de.stoll.nicolas.transport.importer.CSVImporterService;
import de.stoll.nicolas.transport.importer.ProgressBarService;
import de.stoll.nicolas.transport.importer.ThreadHelperService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TransferImportService {

    private final CSVImporterService csvImporterService;

    private final TransferMapper transferMapper;

    private final TransferRepository transferRepository;

    private final StopRepository stopRepository;

    private final ProgressBarService progressBarService;

    private final ThreadHelperService threadHelperService;


    @Transactional
    public void importTransfers() {

        final int batchSize = 100;

        List<TransferDTO> transferDTOs = this.csvImporterService.loadObjectList(TransferDTO.class, "transfer.csv");

        log.info("Importing {} transfers", transferDTOs.size());

        progressBarService.startProgressBar(transferDTOs.size(), "TRANSFERS");

        List<Transfer> transfers = new ArrayList<>();


        List<List<TransferDTO>> batches = ThreadHelperService.splitIntoBatches(transferDTOs, batchSize);

        threadHelperService.runBatch(batches.stream().map(batch -> (ThreadHelperService.Task<Void>) () -> {



            for(TransferDTO transferDTO : batch) {
                Transfer transfer = transferMapper.toTransfer(transferDTO);
                stopRepository.findById(transferDTO.getFromStopId()).ifPresent(transfer::setFromStop);
                stopRepository.findById(transferDTO.getToStopId()).ifPresent(transfer::setToStop);
                transfers.add(transfer);
            }


            progressBarService.updateProgress(batch.size());

            return null;
        }).collect(Collectors.toList()));

        transferRepository.saveAll(transfers);

        progressBarService.complete();

    }
}
