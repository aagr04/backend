package com.role.management.service;

import com.role.management.entity.RoleUser;

import java.util.List;

public interface RoleUserService {

    RoleUser save(RoleUser roleUser);

    RoleUser update(RoleUser roleUser);

    List<RoleUser> getAll();

    RoleUser getById(int id);

    void delete(int id);

}
