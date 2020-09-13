package com.dml.topup.controller.report;

import com.dml.topup.util.SecurityUtil;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Ismael Sadeghi, 6/22/2019 9:24 AM
 */
@RestController
@RequestMapping(value = "v1/operator-status", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OperatorStatusController {

    @Async
    @GetMapping
//    @Timed(
//            value = "operator-status-topup",
//            histogram = true,
//            percentiles = {0.95, 0.99},
//            extraTags = {"version","1.0"}
//    )
    public CompletableFuture<Map<String, Boolean>> findAllTransactions(@RequestParam String operatorName) {
        switch (operatorName) {
            case "mtn":
                return CompletableFuture.completedFuture(Collections.singletonMap("status", SecurityUtil.directTopupMtnStatus));
            case "rightel":
                return CompletableFuture.completedFuture(Collections.singletonMap("status", SecurityUtil.directTopupRightelStatus));
            case "mci":
                return CompletableFuture.completedFuture(Collections.singletonMap("status", SecurityUtil.directTopupMciStatus));
        }
        return null;
    }
}
