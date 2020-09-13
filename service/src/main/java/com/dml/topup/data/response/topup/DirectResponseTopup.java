package com.dml.topup.data.response.topup;

import com.dml.topup.util.MessageConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ismael Sadeghi
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DirectResponseTopup extends Response {

    @ApiModelProperty(example = "3", required = true)
    private String method;

    @ApiModelProperty(example = "4/7/2019 3:57:21 PM", required = true)
    private String requestedDate;

    @ApiModelProperty(value = MessageConstants.AMOUNT_MESSAGE, required = true, example = "10000")
    private String amount;

    @ApiModelProperty(value = MessageConstants.PHONE_DIALER_MESSAGE, example = "9103977079", required = true)
    private Long phoneToCharge;

}