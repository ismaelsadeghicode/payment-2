package com.dml.topup.controller.report;

import com.dml.topup.data.response.Page;
import com.dml.topup.domain.DirectTopup;
import com.dml.topup.service.report.ReportAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @author Ismael Sadeghi, 6/11/2019 9:37 AM
 */
@RestController
@RequestMapping(value = "v1/report-all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ReportAllController {

    private ReportAllService service;

    @Autowired
    public ReportAllController(ReportAllService service) {
        this.service = service;
    }

    @Async
    @GetMapping
//    @Timed(
//            value = "report-all-topup",
//            histogram = true,
//            percentiles = {0.95, 0.99},
//            extraTags = {"version","1.0"}
//    )
    public CompletableFuture<Page<DirectTopup>> findAllReport(@RequestParam int page,
                                                              @RequestParam int size) {
        return CompletableFuture.completedFuture(service.findAllReport(page, size));
    }

}