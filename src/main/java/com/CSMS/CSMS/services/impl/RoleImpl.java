package com.CSMS.CSMS.services.impl;


import com.CSMS.CSMS.Repository.RoleRepo;
import com.CSMS.CSMS.exception.NotFoundException;
import com.CSMS.CSMS.models.Role;
import com.CSMS.CSMS.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleImpl implements RoleService {
    
    @Autowired
    private RoleRepo roleRepo;
    
    @Override
    public List<Role> getAllRole() {
        return roleRepo.findAll();
    }
    
    @Override
    public Role addRole(Role status) {
        return roleRepo.save(status);
    }
    
    @Override
    public void deleteRole(Integer id) {
        try {
            roleRepo.deleteById(id);
        } catch (Exception exception) {
            throw new NotFoundException("Status not found with id " + id);
        }
    }
    
    @Override
    public Role updateRole(Integer id, Role role) {
        Role role1 = roleRepo.findById(id).orElseThrow(() -> new NotFoundException("status not found with id" + id));
        if(role.getRole_description() != null) role1.setRole_description(role.getRole_description());
        return roleRepo.save(role1);
    }
    
    @Override
    public Role getRole(Integer id) {
        return roleRepo.findById(id).orElseThrow(() -> new NotFoundException("status not found with id "  + id));
    }
}