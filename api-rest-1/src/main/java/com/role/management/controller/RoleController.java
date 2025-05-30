package com.role.management.controller;

import com.role.management.entity.Role;
import com.role.management.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role save(@RequestBody Role role){
        return roleService.save(role);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Role update(@RequestBody Role role){
        return roleService.save(role);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Role> getAll(){
        return roleService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Role getById(@PathVariable int id) {
        return roleService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        roleService.delete(id);
    }

}
