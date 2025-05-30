package com.role.management.service.impl;

import com.role.management.entity.Option;
import com.role.management.repository.OptionRepository;
import com.role.management.service.OptionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionServiceImpl implements OptionService {

    private OptionRepository optionRepository;

    public OptionServiceImpl(OptionRepository optionRepository){
        this.optionRepository = optionRepository;
    }

    public Option save(Option option){
        return optionRepository.save(option);
    }

    public Option update(Option option){
        return optionRepository.save(option);
    }

    public List<Option> getAll(){
        return optionRepository.findAll();
    }

    public Option getById(int id){
        return optionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Option with ID " + id + " not found")
        );
    }

    public void delete(int id){
        Optional<Option> option = optionRepository.findById(id);
        option.get().setStatus(false);
        optionRepository.save(option.get());
    }

}
