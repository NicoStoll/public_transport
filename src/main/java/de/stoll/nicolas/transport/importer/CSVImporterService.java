package de.stoll.nicolas.transport.importer;


import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import de.stoll.nicolas.transport.data.Stop;
import de.stoll.nicolas.transport.data.StopRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class CSVImporterService {

    private final StopRepository stopRepository;

    public CSVImporterService(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    @Transactional
    public void loadStopsFromCSV() {

        List<StopDTO> stops = this.loadObjectList(StopDTO.class, "stops.csv");

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

    public <T> List<T> loadObjectList(Class<T> type, String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<T> readValues =
                    mapper.readerFor(type).with(bootstrapSchema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            log.error("Error occurred while loading object list from file {}", fileName, e);
            return Collections.emptyList();
        }
    }
}
