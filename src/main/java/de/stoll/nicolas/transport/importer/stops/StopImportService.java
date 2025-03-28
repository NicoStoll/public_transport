package de.stoll.nicolas.transport.importer.stops;

import de.stoll.nicolas.transport.data.Stop;
import de.stoll.nicolas.transport.data.StopRepository;
import de.stoll.nicolas.transport.importer.CSVImporterService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class StopImportService {


    private final CSVImporterService csvImporter;

    private final StopRepository stopRepository;

    private final StopMapper stopMapper;

    @Transactional
    public void loadStopsFromCSV() {

        List<StopDTO> stops = csvImporter.loadObjectList(StopDTO.class, "stops.csv");

        Map<String, Stop> stopMap = new HashMap<>();

        // Save stops without parent references
        stops.forEach(stopDto -> {
            Stop stop = stopMapper.toStop(stopDto);
            stopMap.put(stop.getStopId(), stop);
        });

        // Save all stops first
        stopRepository.saveAll(stopMap.values());

        // Update parent relationships
        stops.forEach(stopDto -> {
            if (stopDto.getParentStation() != null && !stopDto.getParentStation().isEmpty()) {
                Stop child = stopMap.get(stopDto.getStopId());
                Stop parent = stopMap.get(stopDto.getParentStation());
                if (child != null && parent != null) {
                    child.setParentStop(parent);
                }
            }
        });

        // Save again to update relationships
        stopRepository.saveAll(stopMap.values());

        log.info("Imported {} stops", stopMap.size());
    }

}
