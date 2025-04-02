package de.stoll.nicolas.transport;


import de.stoll.nicolas.transport.importer.stops.StopImportService;
import de.stoll.nicolas.transport.importer.transfer.TransferImportService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StartupRunner implements ApplicationRunner {

    private final StopImportService stopImportService;

    private final TransferImportService transferImportService;

    @Override
    public void run(ApplicationArguments args) {
        stopImportService.loadStopsFromCSV();
        transferImportService.importTransfers();
    }
}
