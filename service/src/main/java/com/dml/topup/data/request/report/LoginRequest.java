package com.dml.topup.data.request.report;

import com.dml.topup.util.MessageConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Ismael Sadeghi, 6/22/2019 12:30 PM
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRequest {

    @NotNull(message = MessageConstants.NOT_NULL)
    private String username;
    @NotNull(message = MessageConstants.NOT_NULL)
    private String password;

}
