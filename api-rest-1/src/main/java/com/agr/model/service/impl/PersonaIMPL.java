package com.agr.model.service.impl;

import com.agr.model.dao.PersonaDao;
import com.agr.model.entitty.Persona;
import com.agr.model.service.IPersona;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaIMPL implements IPersona {

    @Autowired
    private PersonaDao personaDao;

    @Transactional
    @Override
    public Persona save(Persona persona) {
        return personaDao.save(persona);
    }

    @Transactional
    @Override
    public Persona update(Persona persona) {
        Optional<Persona> existingPersona = personaDao.findById(persona.getId());
        if(existingPersona.isPresent()){
            return personaDao.save(persona);
        } else {
            throw new EntityNotFoundException("Persona con ID " + persona.getId() + " no encontrada");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Persona findById(Long id) {
        return personaDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Persona con ID " + id + " no encontrada")
        );
    }

    @Transactional(readOnly = true)
    @Override
    public List<Persona> findAll() {
        Iterable<Persona> iterable = personaDao.findAll();
        List<Persona> lista = new ArrayList<>();
        iterable.forEach(lista::add);
        return lista;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Persona persona = personaDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Persona con ID " + id + " no encontrada")
        );
        personaDao.delete(persona);
    }
}
