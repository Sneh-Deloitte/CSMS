package com.CSMS.CSMS.Repository;

import com.CSMS.CSMS.models.Transaction;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long>  {
    
    @Query(value = "SELECT * FROM transaction WHERE charge_box_id_name=?1",nativeQuery = true)
    Optional<Transaction> getTransactionByChargeBoxId(String charge_box_id_name);

    @Query(value = "SELECT * FROM transaction WHERE id_tag=?1",nativeQuery = true)
    List<Transaction> getTransactionByIdTag(String id_tag);
}
