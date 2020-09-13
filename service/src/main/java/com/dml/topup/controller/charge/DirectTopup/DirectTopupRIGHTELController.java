package com.dml.topup.controller.charge.DirectTopup;

import com.dml.topup.config.Constants;
import com.dml.topup.data.request.topup.ChargeRequestTopup;
import com.dml.topup.data.response.topup.ChargeResponseTopup;
import com.dml.topup.data.response.topup.Response;
import com.dml.topup.service.charge.DirectTopupService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("v1/direct-topup-rightel")
public class DirectTopupRIGHTELController {

    private final DirectTopupService service;

    @Autowired
    public DirectTopupRIGHTELController(DirectTopupService service) {
        this.service = service;
    }

    @Async
    @PostMapping
    @Timed(value = "direct-topup-rightel-topup", histogram = true, percentiles = {0.95, 0.99}, extraTags = {"version", "1.0"})
    public CompletableFuture<Response<ChargeResponseTopup>> charge(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                                   @RequestBody @Valid ChargeRequestTopup request) {
        request.setMethod(41);
        return CompletableFuture.completedFuture(service.charge(request, authorization));
    }
}