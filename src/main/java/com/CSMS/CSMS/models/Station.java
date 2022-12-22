package com.CSMS.CSMS.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "station", uniqueConstraints = {@UniqueConstraint(name = "UniqueLatitudeAndLongitude", columnNames = {"latitude", "longitude"})})
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id", nullable = false)
    private Long id;
    private String address;
    private double latitude;
    private double longitude;
    private String city;
    private String name;
    private String email_id;
    private int contact_no;
    private String image_url;
    private String street;
    private String houseNumber;
    private String country;
    private String zipCode;

    public Station(String zipCode, String country, String houseNumber, String street,String address, double latitude, double longitude, String city, String name, String email_id, int contact_no, String image_url) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.name = name;
        this.email_id = email_id;
        this.contact_no = contact_no;
        this.image_url = image_url;
        this.street = street;
        this.houseNumber= houseNumber;
        this.country=country;
        this.zipCode=zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public int getContact_no() {
        return contact_no;
    }

    public void setContact_no(int contact_no) {
        this.contact_no = contact_no;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

}
