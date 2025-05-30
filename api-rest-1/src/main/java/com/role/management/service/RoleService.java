package com.role.management.service;

import com.role.management.entity.Role;

import java.util.List;

public interface RoleService {

    Role save(Role role);

    Role update(Role role);

    List<Role> getAll();

    Role getById(int id);

    void delete(int id);

}
