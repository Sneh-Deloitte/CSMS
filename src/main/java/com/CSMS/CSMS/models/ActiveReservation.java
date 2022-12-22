package com.CSMS.CSMS.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "activeReservation")
public class ActiveReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long reservation_id;

    public ActiveReservation(Long reservation_id) {
        this.reservation_id = reservation_id;
    }
}
