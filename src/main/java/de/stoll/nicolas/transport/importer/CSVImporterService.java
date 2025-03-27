package de.stoll.nicolas.transport.importer;


import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class CSVImporterService {

    public <T> List<T> loadObjectList(Class<T> type, String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();
            try(MappingIterator<T> readValues = mapper.readerFor(type).with(bootstrapSchema).readValues(file)) {
                return readValues.readAll();
            } catch (IOException e) {
                log.error("Error occurred while loading object list from file {}", fileName, e);
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error("Exception occurred while loading object list from file {}", fileName, e);
            return Collections.emptyList();
        }
    }
}
