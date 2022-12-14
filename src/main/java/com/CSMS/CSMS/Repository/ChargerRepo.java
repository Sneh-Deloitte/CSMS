package com.CSMS.CSMS.Repository;

import com.CSMS.CSMS.models.Charger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargerRepo extends JpaRepository<Charger, Long> {

    @Query(value = "SELECT * FROM charger WHERE station_id=?1",nativeQuery = true)
    List<Charger> getChargerByStationId(long id);

    @Query(value= "SELECT * FROM charger WHERE charger_name=?1", nativeQuery = true)
    List<Charger> getChargerByName(String charger_name);

    @Query(value= "SELECT count(*) FROM charger where station_id=?1", nativeQuery = true)
    Integer countChargerByStationId(long id);
}
