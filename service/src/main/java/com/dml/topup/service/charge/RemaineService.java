package com.dml.topup.service.charge;

import com.dml.topup.data.response.RemainedBalanceResponse;
import com.dml.topup.data.response.topup.RemaineResponseTopup;
import com.dml.topup.data.response.topup.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service for Remain.
 *
 * @author Ismael Sadeghi
 */
@Component
public class RemaineService {

    private final RemainBalanceMciService remainBalanceMciService;
    private final RemainBalanceMtnRightelService remainBalanceMtnRightelService;

    @Autowired
    public RemaineService(RemainBalanceMciService remainBalanceMciService, RemainBalanceMtnRightelService remainBalanceMtnRightelService) {
        this.remainBalanceMciService = remainBalanceMciService;
        this.remainBalanceMtnRightelService = remainBalanceMtnRightelService;
    }

    public Response<RemaineResponseTopup> getRemain(String authorization) {
        Response<RemainedBalanceResponse> mciResponse = remainBalanceMciService.remainedBalance(authorization);
        Response<RemainedBalanceResponse> irancellResponse = remainBalanceMtnRightelService.remainedBalanceMtn(authorization);
        Response<RemainedBalanceResponse> rightelResponse = remainBalanceMtnRightelService.remainedBalanceRightel(authorization);
        RemaineResponseTopup response = new RemaineResponseTopup();
        if (mciResponse.isSuccessful()) {
            response.setMtn(mciResponse.getResponse().getRemainCharge());
        }
        if (irancellResponse.isSuccessful()) {
            response.setIrancell(irancellResponse.getResponse().getRemainCharge());
        }
        if (rightelResponse.isSuccessful()) {
            response.setRightel(rightelResponse.getResponse().getRemainCharge());
        }
        Response<RemaineResponseTopup> result = new Response<>();
        result.setResponse(response);
        result.setSuccessful(Boolean.TRUE);
        return result;
    }
}
