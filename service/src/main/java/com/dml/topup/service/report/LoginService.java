package com.dml.topup.service.report;

import com.dml.topup.data.request.report.LoginRequest;
import com.dml.topup.data.response.topup.Response;
import com.dml.topup.domain.User;
import com.dml.topup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Ismael Sadeghi, 6/22/2019 12:32 PM
 */
@Component
public class LoginService {

    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Response authenticate(LoginRequest request) {
        Response response = new Response();

        User user = userRepository.findByUsername(request.getUsername());
        assert user != null;
        if (user.getPassword().equals(request.getPassword())) {
            response.setSuccessful(Boolean.TRUE);
            return response;
        }
        return response;
    }

    public Response authenticate(String username, String password) {
        Response response = new Response();

        User user = userRepository.findByUsername(username);
        assert user != null;
        if (user.getPassword().equals(password)) {
            response.setSuccessful(Boolean.TRUE);
            return response;
        }
        return response;
    }
}
