package de.stoll.nicolas.transport.importer.agency;

import de.stoll.nicolas.transport.data.Agency;
import de.stoll.nicolas.transport.data.AgencyRepository;
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
public class AgencyImportService {

    private final AgencyRepository agencyRepository;

    private final AgencyMapper agencyMapper;

    private final CSVImporterService csvImporter;

    @Transactional
    public void importAgencies() {

        List<AgencyDTO> agencies = csvImporter.loadObjectList(AgencyDTO.class, "agency.csv");

        Map<String, Agency> agencyMap = new HashMap<>();

        // Map DTOs  to agencies
        agencies.forEach(agencyDTO -> {
            Agency agency = agencyMapper.toAgency(agencyDTO);
            agencyMap.put(agency.getAgencyId(), agency);
        });

        // Save all agencies
        agencyRepository.saveAll(agencyMap.values());

        log.info("Imported {} agencies", agencyMap.size());
    }
}
