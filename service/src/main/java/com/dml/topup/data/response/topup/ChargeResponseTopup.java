package com.dml.topup.data.response.topup;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChargeResponseTopup {

    @ApiModelProperty(example = "139843", required = true)
    private String transactionId;

    @ApiModelProperty(example = "شماره تراکنش منحصر به فرد", required = true)
    private String traceNo;

    @ApiModelProperty(example = "با موفقیت 10000 ریال شارژ شد", required = true)
    private String resDescription;
}