package com.dml.topup.exception;

/**
 * @author Ismael Sadeghi
 */
public enum ErrorCode {

    AUTHORIZATION_EXCEPTION(1000, "AUTHORIZATION_EXCEPTION", "authorization for operations are not permitted"),
    NOT_FOUND_EXCEPTION(1001, "NOT_FOUND_EXCEPTION", "not found exception occurred"),
    BAD_REQUEST_EXCEPTION(1002, "REQUEST_IS_NOT_VALID", "request is not valid"),
    UNKNOWN_EXCEPTION(1003, "UNKNOWN_EXCEPTION", "unknown error occurred"),
    CONNECTION_ERROR(1004, "CONNECTION_ERROR", "connection to the server cannot be established"),
    INTERNAL_SERVER_EXCEPTION(1005, "INTERNAL_SERVER_EXCEPTION", "internal server exception"),
    DATA_NOT_FOUND_EXCEPTION(1006, "DATA_NOT_FOUND_EXCEPTION", "data not found exception"),
    TRANSACTION_FAILED(1007, "TRANSACTION_FAILED", "transaction failed"),
    INVALID_DATE(1008, "INVALID_DATE", "the start date is greater than the end date"),
    INVALID_DATE_FORMAT(1009, "INVALID_DATE_FORMAT", "Invalid date format"),
    INVALID_METHOD(1010, "INVALID_METHOD", "''method' is not valid"),
    TRACENO_IS_DUPLICATE(1011, "TRACENO_IS_DUPLICATE", "'traceNo' is duplicate");

    private Integer code;
    private String httpResponseCode;
    private String description;

    ErrorCode(Integer code, String responseCode, String description) {
        this.code = code;
        this.httpResponseCode = responseCode;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getHttpResponseCode() {
        return httpResponseCode;
    }

    public String getDescription() {
        return description;
    }
}
