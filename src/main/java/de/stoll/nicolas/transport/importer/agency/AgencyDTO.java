package de.stoll.nicolas.transport.importer.agency;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AgencyDTO {

    @JsonProperty("agency_id")
    private String agencyId;

    @JsonProperty("agency_name")
    private String agencyName;

    @JsonProperty("agency_url")
    private String agencyUrl;

    @JsonProperty("agency_timezone")
    private String agencyTimezone;

    @JsonProperty("agency_lang")
    private String agencyLang;

    @JsonProperty("agency_phone")
    private String agencyPhone;

}
