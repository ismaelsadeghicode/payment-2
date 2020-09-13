package com.dml.topup.controller.report;

import com.dml.topup.domain.DirectTopup;
import com.dml.topup.service.report.DailyReportMciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.concurrent.CompletableFuture;

/**
 * @author Ismael Sadeghi, 6/2/2019 7:06 PM
 */
@RestController
@RequestMapping(value = "v1/daily-report-mci", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DailyReportMciController {

    private DailyReportMciService service;

    @Autowired
    public DailyReportMciController(DailyReportMciService service) {
        this.service = service;
    }

    @Async
    @GetMapping
//    @Timed(
//            value = "daily-report-mci-topup",
//            histogram = true,
//            percentiles = {0.95, 0.99},
//            extraTags = {"version","1.0"}
//    )
    public CompletableFuture<Flux<DirectTopup>> findAllTransactions() {
        return CompletableFuture.completedFuture(service.findAllTransactions());
    }
}