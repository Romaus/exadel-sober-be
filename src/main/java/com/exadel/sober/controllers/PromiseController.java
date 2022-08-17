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
    public ResponseEntity<List<Promise>> getPromisesByUserId (@RequestParam Integer userId) {
        return new ResponseEntity<List<Promise>>(
                promiseService.getPromisesForClient(userId),
                HttpStatus.OK
        );
    }
    @PostMapping()
    public ResponseEntity<List<Promise>> addPromiseByUserId (@RequestBody Promise newPromise) {
        return new ResponseEntity<List<Promise>>(
                promiseService.addNewPromiseByUserId(newPromise),
                HttpStatus.OK
        );
    }
    @DeleteMapping()
    public ResponseEntity<List<Promise>> deletePromiseByPromiseId (@RequestParam Integer promiseId) {
        return new ResponseEntity<List<Promise>>(
                promiseService.deletePromise(promiseId),
                HttpStatus.OK
        );
    }
    @PostMapping(path="/addReason")
    public ResponseEntity<List<Promise>> addReasonByPromiseId (@RequestBody Reason reason) {
        return new ResponseEntity<>(
                promiseService.addNewReasonForPromise(reason),
                HttpStatus.OK
        );
    }
}
