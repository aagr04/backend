package com.agr.model.dao;

import com.agr.model.entitty.Persona;
import org.springframework.data.repository.CrudRepository;

public interface PersonaDao extends CrudRepository<Persona, Long> {
}
