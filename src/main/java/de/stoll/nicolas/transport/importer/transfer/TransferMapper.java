package de.stoll.nicolas.transport.importer.transfer;

import de.stoll.nicolas.transport.data.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransferMapper {

    @Mapping(target="transferType", expression = "java(parseInteger(transferDTO.getTransferType()))")
    @Mapping(target="minTransferTime", expression = "java(parseInteger(transferDTO.getMinTransferTime()))")
    Transfer toTransfer(TransferDTO transferDTO);

    default Integer parseInteger(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0;  // Default value, or you could use `null` if needed
        }
        return Integer.parseInt(value);
    }
}
