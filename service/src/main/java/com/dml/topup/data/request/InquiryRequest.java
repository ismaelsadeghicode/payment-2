package com.dml.topup.data.request;

import com.dml.topup.util.MessageConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Ismael Sadeghi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InquiryRequest {

    @ApiModelProperty(hidden = true)
    @JsonProperty("AuthValue")
    private String authValue;

    @ApiModelProperty(hidden = true)
    @JsonProperty("uid")
    private String uid;

    @JsonProperty("providerID")
    @ApiModelProperty(value = MessageConstants.NOT_NULL, example = "4112304", required = true)
    @NotNull(message = MessageConstants.NOT_NULL)
    private String providerID;

    @NotNull(message = MessageConstants.NOT_NULL)
    @ApiModelProperty(value = MessageConstants.NOT_NULL, example = "123456", required = true)
    @JsonProperty("resNum")
    private int resNum;

}
