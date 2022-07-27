package com.exadel.sober.controllers;

import com.exadel.sober.models.Promise;
import com.exadel.sober.models.Reason;
import com.exadel.sober.repositories.PromiseRepository;
import com.exadel.sober.repositories.ReasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/reason")
public class ReasonController {

    @Autowired
    private PromiseRepository promiseRepository;

    @Autowired
    private ReasonRepository reasonRepository;

    @GetMapping(path="/getReasons")
    public @ResponseBody List<Reason> getPromiseByUserName (@RequestParam Integer promiseId) {

        Promise promise = promiseRepository.findById(promiseId).get();
        return promise.getReasons();
    }

    @PostMapping(path="/addReason")
    public @ResponseBody List<Reason> addReasonByPromiseId (@RequestParam Integer promiseId, @RequestParam String description) {

        Promise promise = promiseRepository.findById(promiseId).get();

        Reason r = new Reason();

        r.setPromiseId(promiseId);
        r.setDescription(description);
        reasonRepository.save(r);
        return promiseRepository.findById(promiseId).get().getReasons();
    }
}
