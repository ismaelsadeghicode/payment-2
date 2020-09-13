package com.dml.topup.data.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author Ismael Sadeghi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RemainedBalanceResponse {

    @ApiModelProperty(example = "0", required = true)
    private String remainCharge;

    // Todo after add service package
//    @JsonProperty("remainPackage")
//    @ApiModelProperty(example = "90000", required = true)
//    private String remainPackage;
}
