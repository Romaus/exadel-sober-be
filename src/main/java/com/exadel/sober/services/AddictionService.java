package com.exadel.sober.services;

import com.exadel.sober.models.Addiction;
import com.exadel.sober.repositories.AddictionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddictionService {
    private AddictionRepository addictionRepository;

    public AddictionService(AddictionRepository addictionRepository) {
        this.addictionRepository = addictionRepository;
    }
    public List<Addiction> findAllAddiction() {
        return addictionRepository.findAll();
    }
    public void addNewAddiction(Addiction addiction) {
        addictionRepository.save(addiction);
    }
}
