package de.stoll.nicolas.transport.importer.calendar.scheduleException;

import de.stoll.nicolas.transport.data.Schedule;
import de.stoll.nicolas.transport.data.ScheduleException;
import de.stoll.nicolas.transport.data.ScheduleExceptionRepository;
import de.stoll.nicolas.transport.data.ScheduleRepository;
import de.stoll.nicolas.transport.importer.CSVImporterService;
import de.stoll.nicolas.transport.importer.ProgressBarService;
import de.stoll.nicolas.transport.importer.ThreadHelperService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
@Slf4j
public class ScheduleExceptionImportService {

    private final ScheduleExceptionMapper scheduleExceptionMapper;

    private final ScheduleExceptionRepository scheduleExceptionRepository;

    private final CSVImporterService importerService;

    private final ScheduleRepository scheduleRepository;

    private final ProgressBarService progressBarService;

    private final ThreadHelperService threadHelperService;

    public void importScheduleExceptions() {

        Map<String, Schedule> scheduleCache = new HashMap<>();
        scheduleRepository.findAll().forEach(schedule -> scheduleCache.put(schedule.getServiceId(), schedule));

        List<ScheduleExceptionDTO> scheduleExceptions = this.importerService.loadObjectList(ScheduleExceptionDTO.class, "calendar_dates.csv");

        progressBarService.startProgressBar(scheduleExceptions.size());

        int batchSize = 5000;

        List<List<ScheduleExceptionDTO>> batches = ThreadHelperService.splitIntoBatches(scheduleExceptions, batchSize);

        // Process batches in parallel using ThreadHelperService
        threadHelperService.runBatch(batches.stream().map(batch -> (ThreadHelperService.Task<Void>) () -> {
            Map<String, ScheduleException> scheduleExceptionMap = new HashMap<>();

            for (ScheduleExceptionDTO dto : batch) {
                ScheduleException scheduleException = scheduleExceptionMapper.toScheduleException(dto);
                scheduleException.setSchedule(scheduleCache.get(dto.getServiceId()));
                scheduleExceptionMap.put(dto.getServiceId(), scheduleException);
            }

            scheduleExceptionRepository.saveAll(scheduleExceptionMap.values()); // Batch insert
            progressBarService.updateProgress(batch.size());

            return null;
        }).toList());

        this.progressBarService.complete();

        log.info("Imported {} schedule exceptions", scheduleExceptions.size());
    }
}
