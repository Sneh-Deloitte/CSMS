package com.CSMS.CSMS.response;

public class AuthResponse {
    // Auth response will have email, name and jwt token
    public String email;
    public String name;
    public String token;
    public String mobile;

    public AuthResponse(String email, String name, String mobile, String token){
        this.email = email;
        this.name = name;
        this.token = token;
        this.mobile = mobile;
    }
}
