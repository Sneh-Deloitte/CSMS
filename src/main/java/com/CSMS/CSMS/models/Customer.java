package com.CSMS.CSMS.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// import java.time.String;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer role_id;
    private String customer_email;
    private String customer_firstName;
    private String customer_lastName;
    private Date dob_customer;
    private String customer_gender;
    private String customer_nationality;
    private String customer_phone;
    private String ocpp_tag; // Random generated
    private String parentIdTag; // same as ocpp
    private Date exp_date_ocpp; //exp of ocpp tag
    private String password;

    public Customer(Integer role_id, String customer_email, String customer_firstName, String customer_lastName, Date dob_customer, String customer_gender, String customer_nationality, String customer_phone, String ocpp_tag, String parentIdTag, Date exp_date_ocpp, String password) {
        this.role_id = role_id;
        this.customer_email = customer_email;
        this.customer_firstName = customer_firstName;
        this.customer_lastName = customer_lastName;
        this.dob_customer = dob_customer;
        this.customer_gender = customer_gender;
        this.customer_nationality = customer_nationality;
        this.customer_phone = customer_phone;
        this.ocpp_tag = ocpp_tag;
        this.parentIdTag = parentIdTag;
        this.exp_date_ocpp = exp_date_ocpp;
        this.password = password;
    }
}

