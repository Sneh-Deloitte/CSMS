package com.CSMS.CSMS.Repository;

import com.CSMS.CSMS.models.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepo extends JpaRepository<Station, Long> {

}
