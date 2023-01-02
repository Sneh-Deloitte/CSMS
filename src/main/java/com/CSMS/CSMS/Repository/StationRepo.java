package com.CSMS.CSMS.Repository;

import com.CSMS.CSMS.models.Station;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepo extends JpaRepository<Station, Long> {

    @Query(value = "SELECT * FROM station WHERE email_id=?1",nativeQuery = true)
    List<Station> getAllStationByMail(String email_id);
}
