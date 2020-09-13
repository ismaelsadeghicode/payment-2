package com.dml.topup.data.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Ismael Sadeghi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChargeRequest {

    @JsonProperty("AuthValue")
    private String authValue;
    @JsonProperty("phoneDialer")
    private Long phoneDialer;
    @JsonProperty("phoneToCharge")
    private Long phoneToCharge;
    @JsonProperty("amount")
    private int amount;
    @JsonProperty("orderType")
    private int orderType;
    @JsonProperty("method")
    private int method;
    @JsonProperty("ResNum")
    private int ResNum;
    @JsonProperty("uid")
    private String uid;

}
