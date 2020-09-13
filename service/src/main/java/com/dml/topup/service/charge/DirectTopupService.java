package com.dml.topup.service.charge;

import com.dml.topup.config.Constants;
import com.dml.topup.data.request.ChargeRequest;
import com.dml.topup.data.request.topup.ChargeRequestTopup;
import com.dml.topup.data.response.ChargeResponse;
import com.dml.topup.data.response.topup.ChargeResponseTopup;
import com.dml.topup.data.response.topup.Response;
import com.dml.topup.domain.DirectTopup;
import com.dml.topup.exception.ErrorCode;
import com.dml.topup.repository.LoggingRepository;
import com.dml.topup.util.ObjectUtils;
import com.dml.topup.util.SecurityUtil;
import com.fasterxml.uuid.Generators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Servic for direct com.dml.topup.
 *
 * @author Ismael Sadeghi
 */
@Component
public class DirectTopupService extends Service<ChargeResponse> {

    private static final Logger log = LoggerFactory.getLogger(DirectTopupService.class);
    private final LoggingRepository loggingRepository;
    private int responseCodeOrder;
    private int responseCodeSubmit;
    private SimpleDateFormat fullDatePattern = new SimpleDateFormat(Constants.FULL_DATE_PATTERN);
    private SimpleDateFormat requestFormatDate = new SimpleDateFormat(Constants.REQUEST_FORMAT_DATE);
    private boolean chargeStatus = Boolean.FALSE;
    private DirectTopup directTopup = new DirectTopup();
    private LocalDate localDate;
    private UUID uuid = Generators.timeBasedGenerator().generate();

    @Autowired
    public DirectTopupService(LoggingRepository loggingRepository) {
        super(ChargeResponse.class);
        this.loggingRepository = loggingRepository;
    }

    public Response<ChargeResponseTopup> charge(ChargeRequestTopup request, String authorization) {
        Assert.notNull(authorization);
        Response<ChargeResponseTopup> result = new Response<>();

        if (formatValidationRequestDate(request, result)) return result;
        if (validationMethod(request, result)) return result;
        result = cheackAuthentication(authorization);
        if (cheackAuthentication(authorization).getErrorDescription() != null) {
            return result;
        }

        LocalDateTime dateTime = LocalDateTime.parse(request.getCurrentDateTime(), DateTimeFormatter.ofPattern(Constants.FULL_DATE_PATTERN)); // Todo after version final, will be changed
        localDate = dateTime.toLocalDate();
        if (checkTraceNOUnic(request, result)) return result;

        ChargeResponseTopup response = new ChargeResponseTopup();
        ChargeRequest chargeRequest = new ChargeRequest();
        chargeRequest.setUid(getEnv().getProperty(Constants.SETAREYEK_UID));
        chargeRequest.setAuthValue(SecurityUtil.authValue);
        chargeRequest.setOrderType(Constants.ORDER_TYPE_VALUE);
        chargeRequest.setPhoneDialer(request.getSubscriberNo());
        chargeRequest.setPhoneToCharge(request.getSubscriberNo());
        chargeRequest.setResNum(generateResNo());
        chargeRequest.setAmount(request.getAmount());
        chargeRequest.setMethod(request.getMethod());
        String urlBuilder;
        String urlMciSubmit;
        if (request.getMethod() == 3) {
            urlBuilder = createUrl(Constants.MCI_REST_ORDER_URL);
            urlMciSubmit = createUrl(Constants.MCI_REST_SUBMIT_URL);
        } else if (request.getMethod() == 21 || request.getMethod() == 22 ||
                request.getMethod() == 41 || request.getMethod() == 42) {
            urlBuilder = createUrl(Constants.MTN_RIGHTEL_REST_ORDER_URL);
            urlMciSubmit = createUrl(Constants.MTN_RIGHTEL_REST_SUBMIT_URL);
        } else {
            result.setErrorCode(ErrorCode.INVALID_METHOD.getCode());
            result.setErrorDescription(ErrorCode.INVALID_METHOD.getDescription());
            return result;
        }

        DirectTopup directTopupRequestDB = saveToDB(request, chargeRequest);
//        Mono<ChargeResponse> orderResponse = callWebService(HttpMethod.POST, urlBuilder, chargeRequest, uuid);
//        if (ObjectUtils.isNotNull(orderResponse)) {
//            responseCodeOrder = Objects.requireNonNull(orderResponse.block()).getResCode();
//            response.setTraceNo(request.getTraceNo());
//            Mono<ChargeResponse> resultRestSubmit;
//
//            if (responseCodeOrder == 0) {
//                SubmitRequest submitRequest = new SubmitRequest();
//                submitRequest.setAuthValue(SecurityUtil.authValue);
//                submitRequest.setUid(getEnv().getProperty(Constants.SETAREYEK_UID));
//                submitRequest.setProviderID(Objects.requireNonNull(Objects.requireNonNull(orderResponse.block()).getProviderId()));
//                resultRestSubmit = callWebService(HttpMethod.POST, urlMciSubmit, submitRequest, uuid);
//                responseCodeSubmit = Objects.requireNonNull(resultRestSubmit.block()).getResCode();
//                if (responseCodeSubmit == 0) {
//                    result.setSuccessful(Boolean.TRUE);
//                    chargeStatus = Boolean.TRUE;
//                    response.setResDescription(Objects.requireNonNull(resultRestSubmit.block()).getResDescription());
//                    response.setTransactionId(submitRequest.getProviderID());
//                } else if (responseCodeSubmit == -27 ||
//                        responseCodeSubmit == -7) {
//                    result.setSuccessful(Boolean.TRUE);
//                    chargeStatus = Boolean.TRUE;
//                    response.setTransactionId(submitRequest.getProviderID());
//                    response.setResDescription(MessageConstants.SUCCESSFULLY_CHARGED + request.getAmount());
//                } else {
//                    result.setErrorCode(ErrorCode.BAD_REQUEST_EXCEPTION.getCode());
//                    result.setErrorDescription(Objects.requireNonNull(orderResponse.block()).getErrCode());
//                }
//            } else {
//                result.setErrorCode(ErrorCode.BAD_REQUEST_EXCEPTION.getCode());
//                result.setErrorDescription(Objects.requireNonNull(orderResponse.block()).getErrCode());
//            }
//        } else {
//            result.setErrorCode(ErrorCode.UNKNOWN_EXCEPTION.getCode());
//            result.setErrorDescription(ErrorCode.UNKNOWN_EXCEPTION.getDescription());
//        }
//        assert directTopupRequestDB != null;
//        updateToDB(directTopupRequestDB.getId(), response);
//        result.setResponse(response);
//        if (!result.isSuccessful()) {
//            result.setResponse(null);
//        }
        // mock service
        result.setSuccessful(Boolean.TRUE);
        ChargeResponseTopup responseMock = new ChargeResponseTopup();
        responseMock.setTransactionId("139843");
        responseMock.setTraceNo("963258741");
        responseMock.setResDescription("با موفقیت شارژ شد");
        result.setResponse(responseMock);
        chargeStatus = Boolean.TRUE;
        updateToDB(directTopupRequestDB.getId(), response);
        return result;
    }

    private DirectTopup saveToDB(ChargeRequestTopup request, ChargeRequest chargeRequest) {
        try {
            directTopup.setRequestDateTimeTopup(fullDatePattern.parse(request.getCurrentDateTime()).getTime());
            directTopup.setRequestDateTopup(requestFormatDate.parse(localDate.toString()).getTime());
            directTopup.setAmount(chargeRequest.getAmount());
            directTopup.setMethod(chargeRequest.getMethod());
            directTopup.setTraceNoTopup(request.getTraceNo());
            directTopup.setPostageDate(request.getPostageDate());
            directTopup.setTraceNoTopup(request.getTraceNo());
            directTopup.setResNo(chargeRequest.getResNum());
            directTopup.setSubscriberNo(chargeRequest.getPhoneDialer());
            directTopup.setStatus(Boolean.FALSE);
            return save(directTopup);
        } catch (ParseException e) {
            log.info(String.format("the entered data format is not valid : %s, message: %s", request.getCurrentDateTime(), e.getMessage()));
            return null;
        }
    }

    private void updateToDB(Long id, ChargeResponseTopup response) {
        directTopup.setId(id);
        directTopup.setResCodeOrder(responseCodeOrder);
        directTopup.setResCodeSubmit(responseCodeSubmit);
        directTopup.setStatus(chargeStatus);
        if (responseCodeSubmit == 0) {
            directTopup.setMessageId(response.getTransactionId());
        }
        save(directTopup);
    }

    // Todo after version final, will be changed
    private boolean validationMethod(ChargeRequestTopup request, Response<ChargeResponseTopup> result) {
        if (request.getMethod().
                equals(3)) {
            if (!SecurityUtil.directTopupMciStatus) {
                result.setSuccessful(Boolean.FALSE);
                return true;
            }
        } else if (request.getMethod().
                equals(21) || request.getMethod().
                equals(22)) {
            if (!SecurityUtil.directTopupMtnStatus) {
                result.setSuccessful(Boolean.FALSE);
                return true;
            }
        } else if (request.getMethod().
                equals(41) || request.getMethod().
                equals(42)) {
            if (!SecurityUtil.directTopupRightelStatus) {
                result.setSuccessful(Boolean.FALSE);
                return true;
            }
        }
        return false;
    }

    // Todo after version final, will be changed
    private boolean checkTraceNOUnic(ChargeRequestTopup request, Response<ChargeResponseTopup> result) {
        try {
            if (loggingRepository.findByTraceNoTopupAndRequestDateTopup(request.getTraceNo(), requestFormatDate.parse(localDate.toString()).getTime()) != null) {
                result.setErrorCode(ErrorCode.TRACENO_IS_DUPLICATE.getCode());
                result.setErrorDescription(ErrorCode.TRACENO_IS_DUPLICATE.getDescription());
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Todo after version final, will be changed
    private boolean formatValidationRequestDate(ChargeRequestTopup request, Response<ChargeResponseTopup> result) {
        if (!ObjectUtils.isThisDateValid(request.getCurrentDateTime(), Constants.FULL_DATE_PATTERN)) {
            result.setSuccessful(Boolean.FALSE);
            result.setErrorCode(ErrorCode.INVALID_DATE_FORMAT.getCode());
            result.setErrorDescription(ErrorCode.INVALID_DATE_FORMAT.getDescription());
            return true;
        }
        return false;
    }

    @Async
    @Transactional
    public DirectTopup save(DirectTopup request) {
        loggingRepository.save(request);
        return request;
    }

}