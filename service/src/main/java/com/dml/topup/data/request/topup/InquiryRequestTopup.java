package com.dml.topup.data.request.topup;

import com.dml.topup.util.MessageConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Ismael Sadeghi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InquiryRequestTopup {

    @NotNull(message = MessageConstants.NOT_NULL)
        @ApiModelProperty(value = MessageConstants.NOT_NULL, example = "04-17-2019", required = true)
    private String transactionDate;

    @NotNull(message = MessageConstants.NOT_NULL)
    @ApiModelProperty(value = MessageConstants.NOT_NULL, example = "4112304", required = true)
    private String traceNo;
}
