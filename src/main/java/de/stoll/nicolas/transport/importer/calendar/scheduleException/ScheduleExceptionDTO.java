package de.stoll.nicolas.transport.importer.calendar.scheduleException;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleExceptionDTO {

    @JsonProperty("service_id")
    private String serviceId;

    @JsonProperty("date")
    private String date;

    @JsonProperty("exception_type")
    private String exceptionType;
}
