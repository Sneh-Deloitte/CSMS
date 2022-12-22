package com.CSMS.CSMS.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "charger")
@NoArgsConstructor

public class Charger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "charger_id", nullable = false)
    private Long id;

    private String charger_name;
    private int station_id;
    private int charger_machine_id;
    private int charger_status_id;
    private String charger_type;
    private String charger_op;
    private int booking_rate;
    private int charging_rate;

    public Charger(String charger_name,int station_id, int charger_machine_id, int charger_status_id, String charger_type, String charger_op, int booking_rate, int charging_rate) {
        this.charger_name = charger_name;
        this.station_id = station_id;
        this.charger_machine_id = charger_machine_id;
        this.charger_status_id = charger_status_id;
        this.charger_type = charger_type;
        this.charger_op = charger_op;
        this.booking_rate = booking_rate;
        this.charging_rate = charging_rate;
    }
}
