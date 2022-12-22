package com.CSMS.CSMS.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Customer", uniqueConstraints = {@UniqueConstraint(name = "UniqueEmail", columnNames = { "customer_email"})})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  //  @Column(name = "customer_id", nullable = false)
    private Long id;

    private String role_id;
    private String customer_email;
    private String customer_firstName;
    private String customer_lastName;
    private int dob_customer;
    private String customer_gender;
    private String customer_nationality;
    private int customer_phone;
    private String ocpp_tag;
    private String parentIdTag;
    private String date;

    public Customer(String date, String parentIdTag, Long id, String role_id, String customer_email, String customer_firstName, String customer_lastName, int dob_customer, String customer_gender, String customer_nationality, int customer_phone, String ocpp_tag) {
        this.id = id;
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
        this.date= date;
    }
}
