package de.stoll.nicolas.transport;

import de.stoll.nicolas.transport.importer.CSVImporterService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements ApplicationRunner {

    private final CSVImporterService csvImportService;

    public StartupRunner(CSVImporterService csvImportService) {
        this.csvImportService = csvImportService;
    }

    @Override
    public void run(ApplicationArguments args) {
        csvImportService.loadStopsFromCSV();
    }
}
