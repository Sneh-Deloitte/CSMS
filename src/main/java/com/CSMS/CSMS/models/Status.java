package com.CSMS.CSMS.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private  int status_id;

    private String chargeBoxIdName;
    private String errorCode;

    public Status(String errorCode, String chargeBoxIdName) {
        this.errorCode = errorCode;
        this.chargeBoxIdName= chargeBoxIdName;
    }
}
