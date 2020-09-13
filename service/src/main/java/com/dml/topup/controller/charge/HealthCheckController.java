package com.dml.topup.controller.charge;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Controller for health check.
 *
 * @author Ismael Sadeghi
 */
@RestController
@Api(value = "Health Check", description = "REST API for Health Check", tags = {"HealthCheck"})
public class HealthCheckController {

    @Async
    @GetMapping(value = "v1/health-check", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "health check")
    public CompletableFuture<Map<String, Boolean>> executeHealthCheck() {
        return CompletableFuture.completedFuture(Collections.singletonMap("success", true));
    }
}