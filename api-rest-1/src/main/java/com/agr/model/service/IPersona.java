package com.agr.model.service;

import com.agr.model.entitty.Persona;
import java.util.List;

public interface IPersona {

    Persona save(Persona persona);

    Persona update(Persona persona);

    Persona findById(Long id);

    List<Persona> findAll();

    void delete(Long id);

}
