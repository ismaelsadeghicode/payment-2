package com.dml.topup.service.charge;

import com.dml.topup.config.Constants;
import com.dml.topup.data.request.InquiryRequest;
import com.dml.topup.data.request.topup.InquiryRequestTopup;
import com.dml.topup.data.response.InquiryChargeResponse;
import com.dml.topup.data.response.topup.InquiryChargeResponseTopup;
import com.dml.topup.data.response.topup.Response;
import com.dml.topup.domain.DirectTopup;
import com.dml.topup.exception.ErrorCode;
import com.dml.topup.repository.LoggingRepository;
import com.dml.topup.util.ObjectUtils;
import com.dml.topup.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class InquiryService extends Service<InquiryChargeResponse> {

    private final LoggingRepository loggingRepository;

    private final LoggingRepository getLoggingRepository;
    private SimpleDateFormat fullDatePattern = new SimpleDateFormat(Constants.FULL_DATE_PATTERN);

    @Autowired
    public InquiryService(LoggingRepository loggingRepository, LoggingRepository getLoggingRepository) {
        super(InquiryChargeResponse.class);
        this.loggingRepository = loggingRepository;
        this.getLoggingRepository = getLoggingRepository;
    }

    public Response inquiryChargeRequest(InquiryRequestTopup request, String authorization) {
        Assert.notNull(request);
        Assert.notNull(authorization);
        Response result;
        InquiryChargeResponseTopup response = new InquiryChargeResponseTopup();
        result = cheackAuthentication(authorization);
        if (cheackAuthentication(authorization).getErrorDescription() != null) {
            return result;
        }

        InquiryRequest inquiryRequest = new InquiryRequest();
        inquiryRequest.setAuthValue(SecurityUtil.authValue);
        inquiryRequest.setUid(getEnv().getProperty(Constants.SETAREYEK_UID));
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.SIMPLE_DATE_FORMAT);
            Date dateRequest = dateFormat.parse(request.getTransactionDate());
            long dateRequestDate = dateRequest.getTime();
            DirectTopup charge = getLoggingRepository.findByTraceNoTopupAndRequestDateTopup(request.getTraceNo(), dateRequestDate);
            if (ObjectUtils.isNotNull(charge)) {
                if (charge.getStatus()) {
                    result.setSuccessful(Boolean.TRUE);
                    response.setRequestedDate(fullDatePattern.format(new Date((charge.getRequestDateTimeTopup()))));
                    response.setMethod((long) charge.getMethod());
                    response.setAmount(charge.getAmount());
                    response.setSubscriberNo(charge.getSubscriberNo());
                    response.setResCode(null);
                } else {
                    result.setSuccessful(Boolean.FALSE);
                    response.setRequestedDate(fullDatePattern.format(new Date((charge.getRequestDateTimeTopup()))));
                    response.setMethod((long) charge.getMethod());
                    response.setAmount(charge.getAmount());
                    response.setSubscriberNo(charge.getSubscriberNo());
                    response.setResCode(null);
                }
            } else {
                Response responseError = new Response();
                responseError.setErrorCode(ErrorCode.DATA_NOT_FOUND_EXCEPTION.getCode());
                responseError.setErrorDescription(ErrorCode.DATA_NOT_FOUND_EXCEPTION.getDescription());
                return responseError;
            }

        } catch (Exception e) {
            Response responseError = new Response();
            responseError.setErrorCode(ErrorCode.DATA_NOT_FOUND_EXCEPTION.getCode());
            responseError.setErrorDescription(ErrorCode.DATA_NOT_FOUND_EXCEPTION.getDescription());
            return responseError;
        }
        result.setResponse(response);
        return result;
    }
}