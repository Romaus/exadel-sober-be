package com.exadel.sober.controllers;

import com.exadel.sober.models.Promise;
import com.exadel.sober.models.Reason;
import com.exadel.sober.repositories.PromiseRepository;
import com.exadel.sober.repositoriesImplemetation.ReasonRepositoryImpl;
import com.exadel.sober.services.PromiseService;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/promises")
public class PromiseController {

    private PromiseRepository promiseRepository;

    private ReasonRepositoryImpl reasonRepository;

    private PromiseService promiseService;
    public PromiseController(PromiseRepository promiseRepository,
                             ReasonRepositoryImpl reasonRepository,
                             PromiseService promiseService) {
        this.promiseRepository = promiseRepository;
        this.reasonRepository = reasonRepository;
        this.promiseService = promiseService;
    }
    @GetMapping()
    public @ResponseBody List<Promise> getPromisesByUserId (@RequestParam Integer userId) {
        return promiseService.getPromisesForClient(userId);
    }
    @GetMapping(path="/getReasons")
    public @ResponseBody List<Reason> getReasonsByPromiseId (@RequestParam Integer promiseId) {
        return promiseRepository.findAllReasonsByPromiseId(promiseId);
    }
    @PostMapping()
    public @ResponseBody void addPromiseByUserId (@RequestParam Integer userId, @RequestParam Integer addictionId, @RequestParam Integer days) {
        Promise p = new Promise();
        p.setUserId(userId);
        p.setAddictionId(addictionId);
        p.setExpiredDate(new Timestamp(System.currentTimeMillis() + days*24*3600*1000));
        promiseService.addNewPromiseByUserId(p);
    }
    @PostMapping(path="/addReason")
    public @ResponseBody List<Reason> addReasonByPromiseId (@RequestParam Integer promiseId, @RequestParam String description) {

        Reason r = new Reason();
        r.setPromiseId(promiseId);
        r.setDescription(description);
        reasonRepository.save(r);
        return reasonRepository.findAllByPromiseId(promiseId);
    }
}
