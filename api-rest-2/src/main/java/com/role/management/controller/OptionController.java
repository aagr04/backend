package com.role.management.controller;

import com.role.management.entity.Option;
import com.role.management.service.OptionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/option")
public class OptionController {

    private OptionService optionService;

    public OptionController(OptionService optionService){
        this.optionService = optionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Option save(@RequestBody Option option){
        return optionService.save(option);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Option update(@RequestBody Option option){
        return optionService.save(option);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Option> getAll(){
        return optionService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Option getById(@PathVariable int id) {
        return optionService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        optionService.delete(id);
    }

}
