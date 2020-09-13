package com.dml.topup.data.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Ismael Sadeghi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RemainedBalanceMtnRightelResponse {

    @JsonProperty("MTN_Quota")
    @ApiModelProperty(example = "90000", required = true)
    private String mtn;

    @JsonProperty("Rightel_Quota")
    @ApiModelProperty(example = "100000", required = true)
    private String rightel;
}
