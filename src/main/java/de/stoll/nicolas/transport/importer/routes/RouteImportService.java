package de.stoll.nicolas.transport.importer.routes;

import de.stoll.nicolas.transport.data.AgencyRepository;
import de.stoll.nicolas.transport.data.Route;
import de.stoll.nicolas.transport.data.RouteRepository;
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
public class RouteImportService {

    private final RouteRepository routeRepository;

    private final RouteMapper routeMapper;

    private final CSVImporterService csvImporter;

    private final AgencyRepository agencyRepository;

    public void importRoutes() {

        List<RouteDTO> routes = csvImporter.loadObjectList(RouteDTO.class, "routes.csv");

        Map<String, Route> routeMap = new HashMap<>();

        routes.forEach(routeDTO -> {
            Route route = routeMapper.toRoute(routeDTO);

            //find the correct agency and set it
            agencyRepository.findById(routeDTO.getAgencyId()).ifPresent(route::setAgency);

            routeMap.put(route.getRouteId(), route);
        });

        routeRepository.saveAll(routeMap.values());

        log.info("Imported {} routes", routeMap.size());

    }
}
