package com.role.management.service.impl;

import com.role.management.entity.RoleUser;
import com.role.management.repository.RoleUserRepository;
import com.role.management.service.RoleUserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoleUserServiceImpl implements RoleUserService {

    private RoleUserRepository roleUserRepository;

    public RoleUserServiceImpl(RoleUserRepository roleUserRepository){
        this.roleUserRepository = roleUserRepository;
    }

    public RoleUser save(RoleUser roleUser){
        return roleUserRepository.save(roleUser);
    }

    public RoleUser update(RoleUser roleUser){
        return roleUserRepository.save(roleUser);
    }

    public List<RoleUser> getAll(){
        return roleUserRepository.findAll();
    }

    public RoleUser getById(int id){
        return roleUserRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Role User with ID " + id + " not found")
        );
    }

    public void delete(int id){
        Optional<RoleUser> roleUser = roleUserRepository.findById(id);
        roleUser.get().setStatus(false);
        roleUserRepository.save(roleUser.get());
    }

}
