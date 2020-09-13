package com.dml.topup.data.response;

import com.dml.topup.util.MessageConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Ismael Sadeghi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InquiryChargeResponse {

    @ApiModelProperty(example = "-1", required = true)
    @JsonProperty("ResCode")
    private String resCode;

    @ApiModelProperty(example = "3", required = true)
    @JsonProperty("Fk_MethodType")
    private String method;

    @ApiModelProperty(hidden = true)
    @JsonProperty("OrderType")
    private String orderType;

    @ApiModelProperty(example = "4/7/2019 3:57:21 PM", required = true)
    @JsonProperty("RequestedDate")
    private String requestedDate;

    @ApiModelProperty(value = MessageConstants.AMOUNT_MESSAGE, required = true, example = "10000")
    @JsonProperty("Amount")
    private String amount;

    @ApiModelProperty(value = MessageConstants.PHONE_DIALER_MESSAGE, example = "9103977079", required = true)
    @JsonProperty("PhoneDialer")
    private Long phoneDialer;

    @ApiModelProperty(value = MessageConstants.PHONE_TO_CHARGE_MESSAGE, example = "9103977079", required = true)
    @JsonProperty("PhoneNumber")
    private Long phoneNumber;

    @ApiModelProperty(hidden = true)
    @JsonProperty("ResellerResNum")
    private String resNo;

    @JsonProperty("ErrCode")
    @ApiModelProperty(example = "خطای ارسالی از سمت سرویس دهنده", required = true)
    private String errCode;

    @JsonProperty("resDescription")
    @ApiModelProperty(example = "با موفقیت 10000 ریال شارژ شد", required = true)
    private String resDescription;
}