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
public class ChargeResponse {

    @JsonProperty("ResCode")
    @ApiModelProperty(example = "0", required = true)
    private Integer resCode;

    @JsonProperty("ProviderId")
    @ApiModelProperty(example = "139843", required = true)
    private String providerId;

    @JsonProperty("ErrCode")
    @ApiModelProperty(example = "خطای ارسالی از سمت سرویس دهنده", required = true)
    private String errCode;

    @JsonProperty("resDescription")
    @ApiModelProperty(example = "با موفقیت 10000 ریال شارژ شد", required = true)
    private String resDescription;

    @ApiModelProperty(example = "شماره تراکنش منحصر به فرد", required = true)
    private Integer resNo;
}