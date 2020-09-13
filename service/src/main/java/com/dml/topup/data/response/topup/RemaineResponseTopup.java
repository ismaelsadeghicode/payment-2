package com.dml.topup.data.response.topup;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Ismael Sadeghi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RemaineResponseTopup {

    @ApiModelProperty(example = "90000", required = true)
    private String mtn;

    @ApiModelProperty(example = "10000", required = true)
    private String rightel;

    @ApiModelProperty(example = "20000", required = true)
    private String irancell;
}