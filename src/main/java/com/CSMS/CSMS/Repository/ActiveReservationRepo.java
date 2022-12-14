package com.CSMS.CSMS.Repository;


import com.CSMS.CSMS.models.ActiveReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiveReservationRepo  extends JpaRepository<ActiveReservation,Long> {


}
