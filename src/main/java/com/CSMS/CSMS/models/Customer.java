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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_firstName() {
        return customer_firstName;
    }

    public void setCustomer_firstName(String customer_firstName) {
        this.customer_firstName = customer_firstName;
    }

    public String getCustomer_lastName() {
        return customer_lastName;
    }

    public void setCustomer_lastName(String customer_lastName) {
        this.customer_lastName = customer_lastName;
    }

    public int getDob_customer() {
        return dob_customer;
    }

    public void setDob_customer(int dob_customer) {
        this.dob_customer = dob_customer;
    }

    public String getCustomer_gender() {
        return customer_gender;
    }

    public void setCustomer_gender(String customer_gender) {
        this.customer_gender = customer_gender;
    }

    public String getCustomer_nationality() {
        return customer_nationality;
    }

    public void setCustomer_nationality(String customer_nationality) {
        this.customer_nationality = customer_nationality;
    }

    public int getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(int customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getocpp_tag() {
        return ocpp_tag;
    }

    public void setocpp_tag(String ocpp_tag) {
        this.ocpp_tag = ocpp_tag;
    }

    public String getparent_ocpp_tag() {
        return parentIdTag;
    }

    public void setparent_ocpp_tag(String parentIdTag) {
        this.parentIdTag = parentIdTag;
    }
    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }
}
