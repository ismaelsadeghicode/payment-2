package com.dml.topup.data.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author i.sadeghi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class  RemainedBalanceRequest {

    @ApiModelProperty(hidden = true)
    @JsonProperty("AuthValue")
    private String authValue;

    @ApiModelProperty(hidden = true)
    @JsonProperty("uid")
    private String uuid;
}
