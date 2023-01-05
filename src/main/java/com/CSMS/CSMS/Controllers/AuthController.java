package com.CSMS.CSMS.Controllers;

import com.CSMS.CSMS.request.LoginRequest;
import com.CSMS.CSMS.request.SignupRequest;
import com.CSMS.CSMS.response.AuthResponse;
import com.CSMS.CSMS.services.impl.AuthService;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public AuthResponse loginUser(@RequestBody LoginRequest request) throws MessagingException, IOException, Exception {
        return this.authService.loginUser(request.email, request.password);
    }


    @PostMapping("/signup")
    public AuthResponse signupUser(@RequestBody SignupRequest request) throws MessagingException, IOException {
        return this.authService.signupUser(request);
    }
}

