package com.dml.topup.controller.charge;

import com.dml.topup.config.Constants;
import com.dml.topup.data.response.RemainedBalanceResponse;
import com.dml.topup.data.response.topup.Response;
import com.dml.topup.service.charge.RemainBalanceMtnRightelService;
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
 * Controller for call Remain Balance Rightel servic.
 *
 * @author Ismael Sadeghi
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Balance", description = "REST API for Remain Balance Rightel", tags = {"Remain Balance Rightel"})
public class RemainBalanceRightelController {

    private RemainBalanceMtnRightelService service;

    @Autowired
    public RemainBalanceRightelController(RemainBalanceMtnRightelService service) {
        this.service = service;
    }

    @Async
    @ApiOperation(response = RemainedBalanceResponse.class, value = "Remain Balance Mtn")
    @GetMapping(value = "v1/remain-balance-rightel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @Timed(
//            value = "remain-balance-rightel-topup",
//            histogram = true,
//            percentiles = {0.95, 0.99},
//            extraTags = {"version","1.0"}
//    )
    public CompletableFuture<Response<RemainedBalanceResponse>> remainedBalance(@RequestHeader(value = Constants.AUTHORIZATION) String authorization) {
//        Response<RemainedBalanceResponse> result = service.remainedBalanceRightel(authorization);
//        return CompletableFuture.completedFuture(result);
        Response<RemainedBalanceResponse> result = new Response<>();
        result.setSuccessful(Boolean.TRUE);
        RemainedBalanceResponse responseTopup = new RemainedBalanceResponse();
        responseTopup.setRemainCharge("400000");
        result.setResponse(responseTopup);
        return CompletableFuture.completedFuture(result);
    }
}
