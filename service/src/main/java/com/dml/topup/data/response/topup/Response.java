package com.dml.topup.data.response.topup;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author Ismael Sadeghi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private boolean successful;
    private Integer errorCode;
    private String errorDescription;
    private T response;
}