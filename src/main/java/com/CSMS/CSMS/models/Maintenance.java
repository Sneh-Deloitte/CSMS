package com.CSMS.CSMS.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// import org.joda.time.DateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "maintenance")
@NoArgsConstructor
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    //Include User Id

    private String chargeBoxName;
    private String description;
    private String ticketNo;
    private Date date;
    private String status;

    public Maintenance(String chargeBoxName, String description, Date date, String ticketNo, String status) {
        this.chargeBoxName = chargeBoxName;
        this.description = description;
        this.date = date;
        this.ticketNo = ticketNo;
        this.status= status;
    }
}
