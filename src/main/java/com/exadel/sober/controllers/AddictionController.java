package com.exadel.sober.controllers;

import com.exadel.sober.models.Addiction;
import com.exadel.sober.services.AddictionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/addictions")
public class AddictionController {
    private AddictionService addictionService;
    public AddictionController(AddictionService addictionService) {
        this.addictionService = addictionService;
    }

    @GetMapping()
    public ResponseEntity<List<Addiction>> getAllAddiction() {
        return new ResponseEntity<List<Addiction>>(
                addictionService.findAllAddiction(),
                HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<List<Addiction>> addNewAddiction(@RequestBody Addiction newAddiction) {
        addictionService.addNewAddiction(newAddiction);
        return new ResponseEntity<List<Addiction>>(
                addictionService.findAllAddiction(),
                HttpStatus.OK);
    }
}
