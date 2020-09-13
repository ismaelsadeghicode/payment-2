package com.dml.topup.controller.report;

import com.dml.topup.data.response.report.TotalDailyTransactionsResponse;
import com.dml.topup.domain.DirectTopup;
import com.dml.topup.service.report.DailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Controller for call Micro DailyReport servic.
 *
 * @author Ismael Sadeghi
 */
@RestController
@RequestMapping(value = "v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DailyReportController {

    private DailyReportService service;

    @Autowired
    public DailyReportController(DailyReportService service) {
        this.service = service;
    }


    @Async
    @GetMapping(value = "daily-report/total", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @Timed(
//            value = "daily-report-total-topup",
//            histogram = true,
//            percentiles = {0.95, 0.99},
//            extraTags = {"version","1.0"}
//    )
    public CompletableFuture<TotalDailyTransactionsResponse> totalDailyTransactions() {
        return CompletableFuture.completedFuture(service.totalDailyTransactions());
    }

    @Async
    @RequestMapping(value = "daily-report", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @Timed(
//            value = "daily-report-topup",
//            histogram = true,
//            percentiles = {0.95, 0.99},
//            extraTags = {"version","1.0"}
//    )
    public CompletableFuture<com.dml.topup.data.response.Page<DirectTopup>> filter(@RequestParam Map<String, String> requestParams) {
        return CompletableFuture.completedFuture(service.filter(requestParams));
    }

}