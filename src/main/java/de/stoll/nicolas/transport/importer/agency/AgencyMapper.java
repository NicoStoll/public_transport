package de.stoll.nicolas.transport.importer.agency;

import de.stoll.nicolas.transport.data.Agency;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgencyMapper {

    Agency toAgency(AgencyDTO agencyDTO);
}
