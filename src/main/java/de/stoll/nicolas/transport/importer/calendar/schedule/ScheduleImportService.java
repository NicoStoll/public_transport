package de.stoll.nicolas.transport.importer.calendar.schedule;

import de.stoll.nicolas.transport.data.Schedule;
import de.stoll.nicolas.transport.data.ScheduleRepository;
import de.stoll.nicolas.transport.importer.CSVImporterService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class ScheduleImportService {

    private final CSVImporterService csvImporter;

    private final ScheduleRepository scheduleRepository;

    private final ScheduleMapper serviceMapper;

    @Transactional
    public void importSchedule() {

        List<ScheduleDTO> services = csvImporter.loadObjectList(ScheduleDTO.class, "calendar.csv");

        Map<String, Schedule> serviceMap = new HashMap<>();

        services.forEach(serviceDTO -> {;
            Schedule schedule = serviceMapper.toSchedule(serviceDTO);

            serviceMap.put(schedule.getServiceId(), schedule);
        });

        scheduleRepository.saveAll(serviceMap.values());

        log.info("Imported {} schedules", serviceMap.size());
    }
}
