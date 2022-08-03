package com.exadel.sober.controllers;

import com.exadel.sober.models.Addiction;
import com.exadel.sober.repositoriesImplemetation.AddictionRepositoryImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/addictions")
public class AddictionController {
    private AddictionRepositoryImpl addictionRepository;
    public AddictionController(AddictionRepositoryImpl addictionRepository) {
        this.addictionRepository = addictionRepository;
    }

    @PostMapping()
    public @ResponseBody List<Addiction> addNewAddiction (@RequestBody Addiction newAddiction) {
        addictionRepository.save(newAddiction);
        return addictionRepository.findAll();
    }

    @GetMapping()
    public @ResponseBody List<Addiction> getAllAddiction() {
        return addictionRepository.findAll();
    }
}
