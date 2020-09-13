package com.dml.topup.controller.report;

import com.dml.topup.service.report.TotalTransactionAmountRightelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @author Ismael Sadeghi, 6/2/2019 6:37 PM
 */
@RestController
@RequestMapping(value = "v1/total-transaction-amountt-rightel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TotalTransactionAmountRightelController {

    private TotalTransactionAmountRightelService service;

    @Autowired
    public TotalTransactionAmountRightelController(TotalTransactionAmountRightelService service) {
        this.service = service;
    }

    @Async
    @GetMapping
//    @Timed(
//            value = "total-transaction-amountt-rightel-topup",
//            histogram = true,
//            percentiles = {0.95, 0.99},
//            extraTags = {"version","1.0"}
//    )
    public CompletableFuture<Integer> findTotalTransactionAmountRightel() {
        return CompletableFuture.completedFuture(service.findTotalTransactionAmountRightel());
    }

}
