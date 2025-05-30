package com.role.management.service.impl;

import com.role.management.entity.RoleOption;
import com.role.management.repository.RoleOptionRepository;
import com.role.management.service.RoleOptionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoleOptionServiceImpl implements RoleOptionService {

    private RoleOptionRepository roleOptionRepository;

    public RoleOptionServiceImpl(RoleOptionRepository roleOptionRepository){
        this.roleOptionRepository = roleOptionRepository;
    }

    public RoleOption save(RoleOption roleOption){
        return roleOptionRepository.save(roleOption);
    }

    public RoleOption update(RoleOption roleOption){
        return roleOptionRepository.save(roleOption);
    }

    public List<RoleOption> getAll(){
        return roleOptionRepository.findAll();
    }

    public RoleOption getById(int id){
        return roleOptionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Option with ID " + id + " not found")
        );
    }

    public void delete(int id){
        Optional<RoleOption> roleOption = roleOptionRepository.findById(id);
        roleOption.get().setStatus(false);
        roleOptionRepository.save(roleOption.get());
    }

}
