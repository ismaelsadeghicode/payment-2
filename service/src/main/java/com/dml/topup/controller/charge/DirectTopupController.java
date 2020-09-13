package com.dml.topup.controller.charge;

import com.dml.topup.config.Constants;
import com.dml.topup.data.request.topup.ChargeRequestTopup;
import com.dml.topup.data.response.topup.ChargeResponseTopup;
import com.dml.topup.data.response.topup.Response;
import com.dml.topup.service.charge.DirectTopupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

/**
 * Controller for call direct topup servic.
 *
 * @author Ismael Sadeghi
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Direct topup", description = "REST API for Direct topup", tags = {"Directtopup"})
public class DirectTopupController {

    private final DirectTopupService service;

    @Autowired
    public DirectTopupController(DirectTopupService service) {
        this.service = service;
    }

    @Async
    @ApiOperation(response = ChargeResponseTopup.class, value = "direct topup")
    @PostMapping(value = "v1/direct-topup", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @Timed(
//            value = "direct-topup",
//            histogram = true,
//            percentiles = {0.95, 0.99},
//            extraTags = {"version","1.0"}
//    )
    public CompletableFuture<Response<ChargeResponseTopup>> charge(@RequestHeader(value = Constants.AUTHORIZATION) String authorization,
                                                                   @RequestBody @Valid ChargeRequestTopup request) {
        return CompletableFuture.completedFuture(service.charge(request, authorization));
    }
}