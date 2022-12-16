package com.CSMS.CSMS.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Connector", uniqueConstraints = {@UniqueConstraint(name = "UniqueChagerConnectorConnection", columnNames = { "charger_id", "connector_id" })})

// @Table(uniqueConstraints =
//   { //other constraints
//   @UniqueConstraint(name = "UniqueNumberAndAddress", columnNames = { "personNumber", "address" })})
public class Connector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private int charger_id;
    private int connector_id;
    public Connector(int charger_id, int connector_id) {
        this.charger_id=charger_id;
        this.connector_id =connector_id;
    }
}