package de.stoll.nicolas.transport;

import de.stoll.nicolas.transport.importer.agency.AgencyImportService;
import de.stoll.nicolas.transport.importer.routes.RouteImportService;
import de.stoll.nicolas.transport.importer.stops.StopImportService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StartupRunner implements ApplicationRunner {

    private final StopImportService stopImportService;

    private final AgencyImportService agencyImportService;

    private final RouteImportService routeImportService;

    @Override
    public void run(ApplicationArguments args) {
        agencyImportService.importAgencies();
        routeImportService.importRoutes();
        stopImportService.loadStopsFromCSV();
    }
}
