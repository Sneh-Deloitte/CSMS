package com.CSMS.CSMS.Repository;

import com.CSMS.CSMS.models.Connector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ConnectorRepo extends JpaRepository<Connector, Integer>{
}