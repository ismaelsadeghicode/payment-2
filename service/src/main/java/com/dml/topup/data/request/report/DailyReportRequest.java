package com.dml.topup.data.request.report;

import com.dml.topup.data.request.Request;
import lombok.Data;

/**
 * @author Ismael Sadeghi, 6/19/2019 1:42 PM
 */
@Data
public class DailyReportRequest extends Request {
    private String dailyDate;
}
