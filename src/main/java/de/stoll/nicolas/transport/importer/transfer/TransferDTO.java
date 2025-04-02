package de.stoll.nicolas.transport.importer.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransferDTO {

    @JsonProperty("from_stop_id")
    private String fromStopId;

    @JsonProperty("to_stop_id")
    private String toStopId;

    @JsonProperty("transfer_type")
    private String transferType;

    @JsonProperty("min_transfer_time")
    private String minTransferTime;
}
