package com.dml.topup.data.request.topup;

import com.dml.topup.data.request.Request;
import com.dml.topup.util.MessageConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

/**
 * @author Ismael Sadeghi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChargeRequestTopup extends Request {

    @NotNull(message = MessageConstants.NOT_NULL)
    @ApiModelProperty(value = MessageConstants.PHONE_TO_CHARGE_MESSAGE, example = "9101234567", required = true)
    private Long subscriberNo;

    @NotNull(message = MessageConstants.NOT_NULL)
    @Range(min=10000)
    @ApiModelProperty(value = MessageConstants.AMOUNT_MESSAGE, required = true, example = "10000")
    private Integer amount;

//    @NotNull(message = MessageConstants.NOT_NULL)
    @ApiModelProperty(value = MessageConstants.SPECIFY_OPERATOR_AND_CHARGE_TYPE, example = "3", required = true)
    private Integer method;

    @NotNull(message = MessageConstants.NOT_NULL)
    @ApiModelProperty(value = MessageConstants.TRACE_NO_MESSAGE, example = "12345678912345678920", required = true)
    private String traceNo;

    @NotNull(message = MessageConstants.NOT_NULL)
    @ApiModelProperty(value = MessageConstants.NOT_NULL, example = "04-17-2019 08:48:03", required = true)
    @DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private String currentDateTime;

}
