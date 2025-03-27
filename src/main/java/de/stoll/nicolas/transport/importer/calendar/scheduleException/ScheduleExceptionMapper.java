package de.stoll.nicolas.transport.importer.calendar.scheduleException;

import de.stoll.nicolas.transport.data.ExceptionType;
import de.stoll.nicolas.transport.data.ScheduleException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring", imports = {LocalDate.class})
public interface ScheduleExceptionMapper {

    @Mapping(target= "serviceId", source = "serviceId")
    @Mapping(target = "date", expression = "java(stringToLocalDate(scheduleExceptionDTO.getDate()))")
    @Mapping(target = "exceptionType", expression = "java(mapExceptionType(scheduleExceptionDTO.getExceptionType()))")
    ScheduleException toScheduleException(ScheduleExceptionDTO scheduleExceptionDTO);

    default LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    default ExceptionType mapExceptionType(String exceptionType) {

        int exceptionTypeNum = Integer.parseInt(exceptionType);

        return switch (exceptionTypeNum) {
            case 1 -> ExceptionType.ADDED;
            case 2 -> ExceptionType.REMOVED;
            default -> throw new IllegalArgumentException("Invalid exception type: " + exceptionType);
        };


    }
}
