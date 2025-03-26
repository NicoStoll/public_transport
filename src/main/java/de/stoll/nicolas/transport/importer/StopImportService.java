package de.stoll.nicolas.transport.importer;

import de.stoll.nicolas.transport.data.Stop;
import de.stoll.nicolas.transport.data.StopRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StopImportService {


    private final CSVImporterService csvImporter;

    private final StopRepository stopRepository;

    @Transactional
    public void loadStopsFromCSV() {

        List<StopDTO> stops = this.csvImporter.loadObjectList(StopDTO.class, "stops.csv");

        stops.forEach(stopDto -> {

            Stop stop = new Stop(
                    stopDto.getStopId(),
                    stopDto.getStopName(),
                    stopDto.getStopLat(),
                    stopDto.getStopLon(),
                    Integer.parseInt(stopDto.getLocationType()));

            if (stopDto.getParentStation() != null && !stopDto.getParentStation().isEmpty()) {
                stopRepository.findById(stopDto.getParentStation()).ifPresent(stop::setParentStop);
            }
            stopRepository.save(stop);
        });

        log.info("Stops imported successfully into Neo4j!");
    }

}
