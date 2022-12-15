package com.CSMS.CSMS.Repository;

import com.CSMS.CSMS.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
}