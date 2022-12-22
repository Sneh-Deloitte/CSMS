package com.CSMS.CSMS.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SignupRequest {
    // signup will require email, password and name of the user
    public Integer roleId;
    public String customerEmail;
    public String customerFirstName;
    public String customerLastName;
    public Date dobCustomer;
    public String customerGender;
    public String customerNationality;
    public String customerPhone;
    public String password;

}
