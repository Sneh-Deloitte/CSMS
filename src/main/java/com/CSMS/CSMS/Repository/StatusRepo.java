package com.CSMS.CSMS.Repository;

import com.CSMS.CSMS.models.Status;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepo extends JpaRepository<Status, Integer> {
 
    @Query(value = "SELECT * FROM status WHERE charge_box_id_name=?1",nativeQuery = true)
    Status getStatusByChargeBoxId(String charge_box_id_name);
}
