package org.ipap.scleaner;


import org.ipap.scleaner.model.input.Instructions;
import org.ipap.scleaner.model.output.CleaningResult;
import org.ipap.scleaner.service.CleaningService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class Controller {

    @PostMapping("/instructions")
    public ResponseEntity<CleaningResult> cleanOilSpill(@RequestBody @Valid Instructions instructions) {

        CleaningService cleaningService = new CleaningService();
        CleaningResult cleaningResult = cleaningService.cleanArea(instructions);

        return new ResponseEntity<>(cleaningResult, HttpStatus.OK);
    }

}
