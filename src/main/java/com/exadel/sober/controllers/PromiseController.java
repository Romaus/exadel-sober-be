package com.exadel.sober.controllers;

import com.exadel.sober.models.Promise;
import com.exadel.sober.repositories.PromiseRepository;
import com.exadel.sober.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/promise")
public class PromiseController {

    @Autowired
    private PromiseRepository promiseRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/deletePromise")
    public @ResponseBody List<Promise> deletePromiseByPromiseId (@RequestParam Integer promiseId, @RequestParam String userName) {

        promiseRepository.deleteById(promiseId);

        return userRepository.findByName(userName).getPromises();
    }
}
