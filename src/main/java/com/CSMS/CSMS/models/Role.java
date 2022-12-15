package com.CSMS.CSMS.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "status_id", nullable = false)
    private int role_id;
    private String role_description;
    public Role(int role_id, String role_description) {
        this.role_id=role_id;
        this.role_description = role_description;
    }
}