package com.role.management.service.impl;

import com.role.management.entity.Role;
import com.role.management.repository.RoleRepository;
import com.role.management.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public Role save(Role role){
        return roleRepository.save(role);
    }

    public Role update(Role role){
        return roleRepository.save(role);
    }

    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    public Role getById(int id){
        return roleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Role with ID " + id + " not found")
        );
    }

    public void delete(int id){
        Optional<Role> role = roleRepository.findById(id);
        role.get().setStatus(false);
        roleRepository.save(role.get());
    }

}
