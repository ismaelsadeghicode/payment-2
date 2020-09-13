package com.dml.topup.exception;

import com.dml.topup.data.response.topup.Response;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author Ismael Sadeghi
 */
@RestControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Response response(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        Response response = new Response();
        response.setErrorCode(ErrorCode.NOT_FOUND_EXCEPTION.getCode());
        response.setErrorDescription(String.format("'%s' %s", fieldErrors.get(0).getField(), fieldErrors.get(0).getDefaultMessage()));
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response defaultExceptionHandler(Exception e) {
        Response response = new Response();
        response.setErrorCode(ErrorCode.INTERNAL_SERVER_EXCEPTION.getCode());
        response.setErrorDescription(ErrorCode.INTERNAL_SERVER_EXCEPTION.getDescription());
        return response;
    }
}