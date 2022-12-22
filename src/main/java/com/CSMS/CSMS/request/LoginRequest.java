package com.CSMS.CSMS.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest {
    // Login will require only email and password
    public String email;
    public String password;
}
