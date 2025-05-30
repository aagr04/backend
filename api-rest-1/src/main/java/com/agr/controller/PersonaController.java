package com.agr.controller;

import com.agr.model.entitty.Persona;
import com.agr.model.service.IPersona;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persona")
public class PersonaController {

    private final IPersona personaService;

    public PersonaController(IPersona personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Persona create(@RequestBody Persona persona) {
        return personaService.save(persona);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Persona update(@RequestBody Persona persona) {
        return personaService.update(persona);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Persona getById(@PathVariable Long id) {
        return personaService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Persona> getAll() {
        return personaService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        personaService.delete(id);
    }
}

