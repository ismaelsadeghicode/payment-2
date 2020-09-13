package com.dml.topup.service.charge;

import com.dml.topup.config.Constants;
import com.dml.topup.data.request.RemainedBalanceRequest;
import com.dml.topup.data.response.RemainedBalanceMtnRightelResponse;
import com.dml.topup.data.response.RemainedBalanceResponse;
import com.dml.topup.data.response.topup.Response;
import com.dml.topup.util.SecurityUtil;
import com.fasterxml.uuid.Generators;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Service for Remain Mtn Rightel.
 *
 * @author Ismael Sadeghi
 */
@Component
public class RemainBalanceMtnRightelService extends Service<RemainedBalanceMtnRightelResponse> {

    public RemainBalanceMtnRightelService() {
        super(RemainedBalanceMtnRightelResponse.class);
    }

    public Response<RemainedBalanceResponse> remainedBalanceMtn(String authorization) {
        Assert.notNull(authorization);
        Response<RemainedBalanceResponse> result;
        result = cheackAuthentication(authorization);
        if (cheackAuthentication(authorization).getErrorDescription() != null) {
            return result;
        }
        RemainedBalanceRequest request = new RemainedBalanceRequest();
        RemainedBalanceResponse response = new RemainedBalanceResponse();
        request.setUuid(getEnv().getProperty(Constants.SETAREYEK_UID));
        request.setAuthValue(SecurityUtil.authValue);
        String urlBuilder = createUrl(Constants.MTN_RIGHTEL_REST_GET_REMAINED_BALANCE_URL);
        UUID uuid = Generators.timeBasedGenerator().generate();
        Mono<RemainedBalanceMtnRightelResponse> responseSetareyek = callWebService(HttpMethod.POST, urlBuilder, request, uuid);
        response.setRemainCharge(responseSetareyek.block().getMtn());
        if (response.getRemainCharge() != null) {
            result.setSuccessful(Boolean.TRUE);
            result.setResponse(response);
        }
        return result;
    }

    public Response<RemainedBalanceResponse> remainedBalanceRightel(String authorization) {
        Assert.notNull(authorization);
        Response<RemainedBalanceResponse> result;
        result = cheackAuthentication(authorization);
        if (cheackAuthentication(authorization).getErrorDescription() != null) {
            return result;
        }
        RemainedBalanceRequest request = new RemainedBalanceRequest();
        RemainedBalanceResponse response = new RemainedBalanceResponse();
        request.setUuid(getEnv().getProperty(Constants.SETAREYEK_UID));
        request.setAuthValue(SecurityUtil.authValue);
        String urlBuilder = createUrl(Constants.MTN_RIGHTEL_REST_GET_REMAINED_BALANCE_URL);
        UUID uuid = Generators.timeBasedGenerator().generate();
        Mono<RemainedBalanceMtnRightelResponse> responseSetareyek = callWebService(HttpMethod.POST, urlBuilder, request, uuid);
        response.setRemainCharge(responseSetareyek.block().getRightel());
        if (response.getRemainCharge() != null) {
            result.setSuccessful(Boolean.TRUE);
            result.setResponse(response);
        }
        return result;
    }
}
