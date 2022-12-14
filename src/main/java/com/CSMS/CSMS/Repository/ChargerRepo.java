package com.CSMS.CSMS.Repository;

import com.CSMS.CSMS.models.Charger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargerRepo extends JpaRepository<Charger, Long> {


    @Query(value = "SELECT * FROM charger WHERE station_id=?1",nativeQuery = true)
    List<Charger> getChargerByStationId(long id);

}
