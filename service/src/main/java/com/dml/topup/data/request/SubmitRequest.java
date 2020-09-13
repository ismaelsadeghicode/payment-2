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
public class SubmitRequest {

    @ApiModelProperty(hidden = true)
    @JsonProperty("AuthValue")
    private String authValue;

    @JsonProperty("ProviderId")
    @NotNull(message = MessageConstants.NOT_NULL)
    private String providerID;

    @ApiModelProperty(hidden = true)
    @JsonProperty("uid")
    private String uid;

}
