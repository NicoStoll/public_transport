package de.stoll.nicolas.transport.importer.calendar.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScheduleDTO {

    @JsonProperty("service_id")
    private String serviceId;

    @JsonProperty("monday")
    private String monday;

    @JsonProperty("tuesday")
    private String tuesday;

    @JsonProperty("wednesday")
    private String wednesday;

    @JsonProperty("thursday")
    private String thursday;

    @JsonProperty("friday")
    private String friday;

    @JsonProperty("saturday")
    private String saturday;

    @JsonProperty("sunday")
    private String sunday;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;
}
