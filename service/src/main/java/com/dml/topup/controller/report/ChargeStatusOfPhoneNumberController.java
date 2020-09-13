package com.dml.topup.controller.report;

import com.dml.topup.domain.DirectTopup;
import com.dml.topup.service.report.ChargeStatusOfPhoneNumberService;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @author Ismael Sadeghi, 6/11/2019 3:17 PM
 */
@RestController
@RequestMapping(value = "v1/charge-status-of-phone-number", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ChargeStatusOfPhoneNumberController {

    private ChargeStatusOfPhoneNumberService service;

    public ChargeStatusOfPhoneNumberController(ChargeStatusOfPhoneNumberService service) {
        this.service = service;
    }

    @Async
    @GetMapping
//    @Timed(
//            value = "charge-status-of-phone-number-topup",
//            histogram = true,
//            percentiles = {0.95, 0.99},
//            extraTags = {"version","1.0"}
//    )
    public CompletableFuture<com.dml.topup.data.response.Page<DirectTopup>> getChargeStatusOfPhoneNumber(@RequestParam String subscriberNo,
                                                                                                         @RequestParam int page,
                                                                                                         @RequestParam int size) {
        return CompletableFuture.completedFuture(service.getChargeStatusOfPhoneNumber(Long.valueOf(subscriberNo), page, size));
    }
}
