package de.stoll.nicolas.transport.importer.stops;

import de.stoll.nicolas.transport.data.Stop;
import de.stoll.nicolas.transport.data.StopRepository;
import de.stoll.nicolas.transport.importer.CSVImporterService;
import de.stoll.nicolas.transport.importer.ProgressBarService;
import de.stoll.nicolas.transport.importer.ThreadHelperService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class StopImportService {


    private final CSVImporterService csvImporter;

    private final StopRepository stopRepository;

    private final StopMapper stopMapper;

    private final ProgressBarService progressBarService;

    private final ThreadHelperService threadHelperService;

    @Transactional
    public void loadStopsFromCSV() {

        List<StopDTO> stops = csvImporter.loadObjectList(StopDTO.class, "stops.csv");

        progressBarService.startProgressBar(stops.size(), "STOPS");

        int batchSize = 500;

        List<List<StopDTO>> batches = ThreadHelperService.splitIntoBatches(stops, batchSize);

        //Process batches in parallel using ThreadHelperService
        threadHelperService.runBatch(batches.stream().map(batch -> (ThreadHelperService.Task<Void>) () -> {

            Map<String, Stop> stopMap = batch.stream()
                    .map(stopMapper::toStop)
                    .collect(Collectors.toMap(Stop::getStopId, stop -> stop));

            //save all stops
            stopRepository.saveAll(stopMap.values());
            progressBarService.updateProgress(batch.size());

            return null;
        }).toList());

        progressBarService.complete();

        progressBarService.startProgressBar(stops.size(), "PARENT STOPS");

        threadHelperService.runBatch(batches.stream().map(batch -> (ThreadHelperService.Task<Void>) () -> {

            // Update parent relationships
            batch.forEach(stopDto -> {
                // Skip the update if the parent station is null or an empty string
                if (stopDto.getParentStation() != null && !stopDto.getParentStation().isEmpty()) {
                    Optional<Stop> childOpt = stopRepository.findById(stopDto.getStopId());
                    Optional<Stop> parentOpt = stopRepository.findById(stopDto.getParentStation());

                    // Ensure both child and parent stops exist and are valid
                    if (childOpt.isPresent() && parentOpt.isPresent()) {

                        Stop child = childOpt.get();
                        child.setParentStop(parentOpt.get());

                        // Save the child only if the parent relationship is valid
                        stopRepository.save(child);
                    }
                }
            });

            progressBarService.updateProgress(batch.size());

            return null;

        }).toList());


        this.progressBarService.complete();

        log.info("Imported {} stops", stops.size());
    }

}
