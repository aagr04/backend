package com.role.management.controller;

import com.role.management.entity.RoleUser;
import com.role.management.service.RoleUserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role/user")
public class RoleUserController {

    private RoleUserService roleUserService;

    public RoleUserController(RoleUserService roleUserService){
        this.roleUserService = roleUserService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleUser save(@RequestBody RoleUser roleUser){
        return roleUserService.save(roleUser);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public RoleUser update(@RequestBody RoleUser roleUser){
        return roleUserService.save(roleUser);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoleUser> getAll(){
        return roleUserService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleUser getById(@PathVariable int id) {
        return roleUserService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        roleUserService.delete(id);
    }

}
