package com.exadel.sober.controllers;

import com.exadel.sober.repositories.AddictionRepository;
import com.exadel.sober.models.Addiction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path="/addiction")
public class AddictionController {
    @Autowired
    private AddictionRepository addictionRepository;

    @PostMapping(path="/add")
    public @ResponseBody Iterable<Addiction> addNewAddiction (@RequestBody Addiction newAddiction) {
        addictionRepository.save(newAddiction);
        return addictionRepository.findAll();
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Addiction> getAllAddiction() {
        return addictionRepository.findAll();
    }
}
