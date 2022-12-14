package com.CSMS.CSMS.Controllers;
import com.CSMS.CSMS.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.CSMS.CSMS.Repository.RoleRepo;
import com.CSMS.CSMS.models.Role;
import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepo roleRepo;
    
    @GetMapping("/role/all")
    public List<Role> getAllRoles()
    {
        return roleService.getAllRole();
    }

    @GetMapping("/role/{id}")
    public Role getRoleById(@PathVariable Integer id)
    {
        return roleService.getRole(id);
    }

    @PostMapping("/role/add")
    public Role addRole(@RequestBody Role role)
    {
        return roleService.addRole(role);
    }


    @PutMapping("/role/{id}")
    public Role udpateRole(@PathVariable Integer id, @RequestBody Role role)
    {
        return roleService.updateRole(id, role);
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable Integer id){
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
