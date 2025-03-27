package de.stoll.nicolas.transport.importer.calendar.schedule;

import de.stoll.nicolas.transport.data.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring", imports = {LocalDate.class})
public interface ScheduleMapper {

    @Mapping(target = "monday", expression = "java(stringToBoolean(scheduleDTO.getMonday()))")
    @Mapping(target = "tuesday", expression = "java(stringToBoolean(scheduleDTO.getTuesday()))")
    @Mapping(target = "wednesday", expression = "java(stringToBoolean(scheduleDTO.getWednesday()))")
    @Mapping(target = "thursday", expression = "java(stringToBoolean(scheduleDTO.getThursday()))")
    @Mapping(target = "friday", expression = "java(stringToBoolean(scheduleDTO.getFriday()))")
    @Mapping(target = "saturday", expression = "java(stringToBoolean(scheduleDTO.getSaturday()))")
    @Mapping(target = "sunday", expression = "java(stringToBoolean(scheduleDTO.getSunday()))")
    @Mapping(target = "startDate", expression = "java(stringToLocalDate(scheduleDTO.getStartDate()))")
    @Mapping(target = "endDate", expression = "java(stringToLocalDate(scheduleDTO.getEndDate()))")
    Schedule toSchedule(ScheduleDTO scheduleDTO);

    default boolean stringToBoolean(String value) {
        return "1".equals(value);
    }

    default LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
