package com.role.management.service;

import com.role.management.entity.Option;

import java.util.List;

public interface OptionService {

    Option save(Option option);

    Option update(Option option);

    List<Option> getAll();

    Option getById(int id);

    void delete(int id);

}
