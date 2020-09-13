package com.dml.topup.controller.charge;

import com.dml.topup.config.Constants;
import com.dml.topup.data.response.topup.RemaineResponseTopup;
import com.dml.topup.data.response.topup.Response;
import com.dml.topup.service.charge.RemaineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * Controller for call Remain Balance.
 *
 * @author Ismael Sadeghi
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Balance", description = "REST API for Remain Balance", tags = {"Remain Balance"})
public class RemaineController {

    private RemaineService service;

    @Autowired
    public RemaineController(RemaineService service) {
        this.service = service;
    }

    @Async
    @ApiOperation(response = RemaineResponseTopup.class, value = "Remain Balance")
    @GetMapping(value = "v1/remain", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @Timed(
//            value = "remain-topup",
//            histogram = true,
//            percentiles = {0.95, 0.99},
//            extraTags = {"version","1.0"}
//    )
    public CompletableFuture<Response<RemaineResponseTopup>> remainedBalance(@RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
//        return CompletableFuture.completedFuture(service.getRemain(authorization));

        // mock service
        Response<RemaineResponseTopup> result = new Response<>();
        result.setSuccessful(Boolean.TRUE);
        RemaineResponseTopup responseTopup = new RemaineResponseTopup();
        responseTopup.setIrancell("400000");
        responseTopup.setMtn("300000");
        responseTopup.setRightel("500000");
        result.setResponse(responseTopup);
        return CompletableFuture.completedFuture(result);
    }
}