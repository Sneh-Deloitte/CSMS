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
    @Column(name = "status_id", nullable = false)
    private  int status_id;

    private String status_description;

    public Status(int status_id, String status_description) {
        this.status_id=status_id;
        this.status_description = status_description;
    }
}
