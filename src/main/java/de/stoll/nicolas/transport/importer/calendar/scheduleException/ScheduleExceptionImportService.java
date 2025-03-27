package de.stoll.nicolas.transport.importer.calendar.scheduleException;

import de.stoll.nicolas.transport.data.Schedule;
import de.stoll.nicolas.transport.data.ScheduleException;
import de.stoll.nicolas.transport.data.ScheduleExceptionRepository;
import de.stoll.nicolas.transport.data.ScheduleRepository;
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
public class ScheduleExceptionImportService {

    private final ScheduleExceptionMapper scheduleExceptionMapper;

    private final ScheduleExceptionRepository scheduleExceptionRepository;

    private final CSVImporterService importerService;

    private final ScheduleRepository scheduleRepository;

    public void importScheduleExceptions() {

        List<ScheduleExceptionDTO> scheduleExceptions = this.importerService.loadObjectList(ScheduleExceptionDTO.class, "calendar_dates.csv");

        scheduleExceptions.stream()
                .map(scheduleExceptionMapper::toScheduleException)
                .forEach(scheduleException -> {

                    this.scheduleRepository.findById(scheduleException.getServiceId()).ifPresent(scheduleException::setSchedule);
                    this.scheduleExceptionRepository.save(scheduleException);
        });

        log.info("Schedule exceptions imported successfully into Neo4j!");
    }
}
