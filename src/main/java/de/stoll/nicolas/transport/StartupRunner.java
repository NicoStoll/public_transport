package de.stoll.nicolas.transport;

import de.stoll.nicolas.transport.importer.agency.AgencyImportService;
import de.stoll.nicolas.transport.importer.calendar.schedule.ScheduleImportService;
import de.stoll.nicolas.transport.importer.calendar.scheduleException.ScheduleExceptionImportService;
import de.stoll.nicolas.transport.importer.routes.RouteImportService;
import de.stoll.nicolas.transport.importer.stops.StopImportService;
import de.stoll.nicolas.transport.importer.trips.TripImportService;
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

    private final ScheduleImportService scheduleImportService;

    private final ScheduleExceptionImportService scheduleExceptionImportService;

    private final TripImportService tripImportService;

    @Override
    public void run(ApplicationArguments args) {
        agencyImportService.importAgencies();
        routeImportService.importRoutes();
        stopImportService.loadStopsFromCSV();
        scheduleImportService.importSchedule();
        scheduleExceptionImportService.importScheduleExceptions();
        tripImportService.importTrips();
    }
}
