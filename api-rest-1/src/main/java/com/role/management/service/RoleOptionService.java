package com.role.management.service;

import com.role.management.entity.RoleOption;

import java.util.List;

public interface RoleOptionService {

    RoleOption save(RoleOption roleOption);

    RoleOption update(RoleOption roleOption);

    List<RoleOption> getAll();

    RoleOption getById(int id);

    void delete(int id);

}
