package com.dml.topup.controller.report;

import com.dml.topup.data.request.report.LoginRequest;
import com.dml.topup.data.response.topup.Response;
import com.dml.topup.service.report.LoginService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

/**
 * @author Ismael Sadeghi, 6/22/2019 12:25 PM
 */
@RestController
@RequestMapping(value = "v1/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginController {

    private final LoginService service;

    @Autowired
    public LoginController(LoginService service) {
        this.service = service;
    }

    @Async
    @PostMapping
    @Timed(
            value = "login-topup",
            histogram = true,
            percentiles = {0.95, 0.99},
            extraTags = {"version", "1.0"}
    )
    public CompletableFuture<Response> login(@RequestBody @Valid LoginRequest request) {
        return CompletableFuture.completedFuture(service.authenticate(request));
    }

    @Async
    @GetMapping
    public CompletableFuture<Response> login(@RequestParam String username,
                                             @RequestParam String password) {
        return CompletableFuture.completedFuture(service.authenticate(username, password));
    }
}
