package com.dml.topup.controller.report;

import com.dml.topup.domain.DirectTopup;
import com.dml.topup.service.report.DailyReportMtnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.concurrent.CompletableFuture;

/**
 * @author Ismael Sadeghi, 6/2/2019 7:04 PM
 */
@RestController
@RequestMapping(value = "v1/daily-report-mtn", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DailyReportMtnController {

    private DailyReportMtnService service;

    @Autowired
    public DailyReportMtnController(DailyReportMtnService service) {
        this.service = service;
    }

    @Async
    @GetMapping
//    @Timed(
//            value = "daily-report-mtn-topup",
//            histogram = true,
//            percentiles = {0.95, 0.99},
//            extraTags = {"version","1.0"}
//    )
    public CompletableFuture<Flux<DirectTopup>> findAllTransactions() {
        return CompletableFuture.completedFuture(service.findAllTransactions());
    }
}
