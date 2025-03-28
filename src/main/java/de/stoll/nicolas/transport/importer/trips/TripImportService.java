package de.stoll.nicolas.transport.importer.trips;

import de.stoll.nicolas.transport.data.*;
import de.stoll.nicolas.transport.importer.CSVImporterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class TripImportService {

    private final TripMapper tripMapper;

    private final TripRepository tripRepository;

    private final CSVImporterService csvImporter;

    private final RouteRepository routeRepository;

    private final ScheduleRepository scheduleRepository;

    public void importTrips() {

        HashMap<String, Route> routesCache = new HashMap<>();
        this.routeRepository.findAll().forEach(route -> routesCache.put(route.getRouteId(), route));

        Map<String, Schedule> scheduleCache = new HashMap<>();
        this.scheduleRepository.findAll().forEach(schedule -> scheduleCache.put(schedule.getServiceId(), schedule));

        List<TripDTO> trips = this.csvImporter.loadObjectList(TripDTO.class, "trips.csv");

        Map<String, Trip> tripMap = new HashMap<>();

        trips.forEach(tripDTO -> {

            Trip trip = this.tripMapper.toTrip(tripDTO);

            trip.setRoute(routesCache.get(tripDTO.getRouteId()));

            trip.setSchedule(scheduleCache.get(tripDTO.getServiceId()));

            tripMap.put(trip.getTripId(), trip);
        });

        this.tripRepository.saveAll(tripMap.values());

        log.info("Imported {} trips", trips.size());
    }
}
