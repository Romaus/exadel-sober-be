package com.exadel.sober.controllers;

import com.exadel.sober.models.Promise;
import com.exadel.sober.models.Reason;
import com.exadel.sober.services.PromiseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/promises")
public class PromiseController {

    private PromiseService promiseService;
    public PromiseController( PromiseService promiseService ) {
        this.promiseService = promiseService;
    }

    @GetMapping()
    ResponseEntity<List<Promise>> getPromisesByUserId (@RequestParam Integer userId) {
        return new ResponseEntity<List<Promise>>(
                promiseService.getPromisesForClient(userId),
                HttpStatus.OK
        );
    }
    @PostMapping()
    ResponseEntity<List<Promise>> addPromiseByUserId (@RequestParam Integer userId,
                                                      @RequestParam Integer addictionId,
                                                      @RequestParam Integer days) {
        return new ResponseEntity<List<Promise>>(
                promiseService.addNewPromiseByUserId(userId, addictionId, days),
                HttpStatus.OK
        );
    }
    @DeleteMapping()
    ResponseEntity<List<Promise>> deletePromiseByPromiseId (@RequestParam Integer promiseId) {
        return new ResponseEntity<List<Promise>>(
                promiseService.deletePromise(promiseId),
                HttpStatus.OK
        );
    }
    @PostMapping(path="/addReason")
    ResponseEntity<List<Promise>> addReasonByPromiseId (@RequestBody Reason reason) {
        return new ResponseEntity<>(
                promiseService.addNewReasonForPromise(reason),
                HttpStatus.OK
        );
    }
}
