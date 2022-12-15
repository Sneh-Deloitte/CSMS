package com.CSMS.CSMS.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "booking")
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    //Include User Id

    private int customer_id;
    private String booking_id;
    private int station_id;
    private int charger_id;
    private int connector_id;
    private String start_time;
    private String end_time;
    private int cost;
    private String payment_mode;
    private int payment_status;
    private String date;

    public Booking(int customer_id, String booking_id, int station_id, int charger_id, int connector_id, String start_time, String end_time, int cost, String payment_mode, int payment_status, String date) {
        this.customer_id = customer_id;
        this.booking_id = booking_id;
        this.station_id = station_id;
        this.charger_id = charger_id;
        this.connector_id = connector_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.cost = cost;
        this.payment_mode = payment_mode;
        this.payment_status = payment_status;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public String setBooking_id(String booking_id) {
        this.booking_id = booking_id;
        return booking_id;
    }

    public int getStation_id() {
        return station_id;
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }

    public int getCharger_id() {
        return charger_id;
    }

    public void setCharger_id(int charger_id) {
        this.charger_id = charger_id;
    }

    public int getConnector_id() {
        return connector_id;
    }

    public void setConnector_id(int connector_id) {
        this.connector_id = connector_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public int getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(int payment_status) {
        this.payment_status = payment_status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

