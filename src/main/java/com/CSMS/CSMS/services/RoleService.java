package com.CSMS.CSMS.services;
import com.CSMS.CSMS.models.Role;

import java.util.List;

public interface RoleService {

    public List<Role> getAllRole();

    public Role addRole(Role Role);

    public void deleteRole(Integer id);

    public Role updateRole(Integer id,Role role);
    
    public Role getRole(Integer id);
}