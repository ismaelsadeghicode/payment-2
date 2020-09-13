package com.dml.topup.data.response.report;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author Ismael Sadeghi, 6/22/2019 10:53 AM
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TotalDailyTransactionsResponse {

    private int mtn;
    private int mci;
    private int rightel;
}
