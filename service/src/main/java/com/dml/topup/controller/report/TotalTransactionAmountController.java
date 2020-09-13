package com.dml.topup.controller.report;

import com.dml.topup.service.report.TotalTransactionAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @author Ismael Sadeghi, 6/2/2019 6:43 PM
 */
@RestController
@RequestMapping(value = "v1/total-transaction-amountt", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TotalTransactionAmountController {

    private TotalTransactionAmountService service;

    @Autowired
    public TotalTransactionAmountController(TotalTransactionAmountService service) {
        this.service = service;
    }

    @Async
    @GetMapping
//    @Timed(
//            value = "total-transaction-amountt-topup",
//            histogram = true,
//            percentiles = {0.95, 0.99},
//            extraTags = {"version","1.0"}
//    )
    public CompletableFuture<Integer> findTotalTransactionAmount() {
        return CompletableFuture.completedFuture(service.findTotalTransactionAmount());
    }

}
