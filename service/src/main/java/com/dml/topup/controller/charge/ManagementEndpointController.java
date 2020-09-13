package com.dml.topup.controller.charge;

import com.dml.topup.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @author Ismael Sadeghi, 6/8/2019 12:44 PM
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Management Endpoint", description = "REST API for Management Endpoint", tags = {"Management Endpoint"})
public class ManagementEndpointController {

    @Async
    @ApiOperation(value = "change-health-status")
    @GetMapping(value = "v1/change-health-status", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @Timed(
//            value = "change-health-status-topup",
//            histogram = true,
//            percentiles = {0.95, 0.99},
//            extraTags = {"version","1.0"}
//    )
    public CompletableFuture<Map<String, Boolean>> executeHealthCheck(@RequestParam Boolean status) {
        SecurityUtil.healthCheckStatus = status;
        return CompletableFuture.completedFuture(Collections.singletonMap("healthCheckStatus", status));
    }

    @Async
    @ApiOperation(value = "direct topup mci status")
    @GetMapping(value = "v1/direct-topup-mci-status", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @Timed(
//            value = "direct-topup-mci-status-topup",
//            histogram = true,
//            percentiles = {0.95, 0.99},
//            extraTags = {"version","1.0"}
//    )
    public CompletableFuture<Map<String, Boolean>> executeDirectTopupMci(@RequestParam Boolean status) {
        SecurityUtil.directTopupMciStatus = status;
        return CompletableFuture.completedFuture(Collections.singletonMap("directTopupMciStatus", status));
    }

    @Async
    @ApiOperation(value = "direct topup mtn status")
    @GetMapping(value = "v1/direct-topup-mtn-status", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @Timed(
//            value = "direct-topup-mtn-status-topup",
//            histogram = true,
//            percentiles = {0.95, 0.99},
//            extraTags = {"version","1.0"}
//    )
    public CompletableFuture<Map<String, Boolean>> executeDirectTopupMtn(@RequestParam Boolean status) {
        SecurityUtil.directTopupMtnStatus = status;
        return CompletableFuture.completedFuture(Collections.singletonMap("directTopupMtnStatus", status));
    }

    @Async
    @ApiOperation(value = "direct topup rightel status")
    @GetMapping(value = "v1/direct-topup-rightel-status", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @Timed(
//            value = "direct-topup-rightel-status-topup",
//            histogram = true,
//            percentiles = {0.95, 0.99},
//            extraTags = {"version","1.0"}
//    )
    public CompletableFuture<Map<String, Boolean>> executeDirectTopupRightel(@RequestParam Boolean status) {
        SecurityUtil.directTopupRightelStatus = status;
        return CompletableFuture.completedFuture(Collections.singletonMap("directTopupRightelStatus", status));
    }
}
