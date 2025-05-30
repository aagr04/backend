package com.role.management.controller;

import com.role.management.entity.RoleOption;
import com.role.management.service.RoleOptionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role/option")
public class RoleOptionController {

    private RoleOptionService roleOptionService;

    public RoleOptionController(RoleOptionService roleOptionService){
        this.roleOptionService = roleOptionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleOption save(@RequestBody RoleOption roleOption){
        return roleOptionService.save(roleOption);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public RoleOption update(@RequestBody RoleOption roleOption){
        return roleOptionService.save(roleOption);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoleOption> getAll(){
        return roleOptionService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleOption getById(@PathVariable int id) {
        return roleOptionService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        roleOptionService.delete(id);
    }

}
