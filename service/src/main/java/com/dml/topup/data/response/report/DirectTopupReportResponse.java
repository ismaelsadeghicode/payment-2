package com.dml.topup.data.response.report;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author Ismael Sadeghi, 6/22/2019 2:20 PM
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DirectTopupReportResponse {

    private Long id;
    private Boolean status;
    private Long subscriberNo;
    private int amount;
    private int method;
    private Long requestDateTopup;
    private String traceNoTopup;

}
