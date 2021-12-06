package learn.rockClimbing.controllers;

import learn.rockClimbing.domain.ClimberService;
import learn.rockClimbing.domain.Result;
import learn.rockClimbing.models.Climber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/climber")
public class ClimberController {
    private final ClimberService service;

    public ClimberController(ClimberService service) {
        this.service = service;
    }

    @GetMapping
    public List<Climber> findAllClimbers() {
        return service.findAllClimbers();
    }

    @GetMapping("/{climberId}")
    public ResponseEntity<Climber> findClimberById(@PathVariable int climberId) {
        Climber climber = service.findByClimberId(climberId);
        if (climber == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(climber);
    }

    @PutMapping
    public ResponseEntity<Object> addClimber(@RequestBody @Valid Climber climber, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Result<Climber> result = service.addClimber(climber);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }

    @PostMapping("/{climberId}")
    public ResponseEntity<Object> editClimber(@PathVariable int climberId, @RequestBody @Valid Climber climber,
                                              BindingResult bindingResult) {
        if (climberId != climber.getClimberId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Result<Climber> result = service.editClimber(climber);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{climberId}")
    public ResponseEntity<Void> deleteClimberById(@PathVariable int climberId) {
        if (service.deleteClimberById(climberId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
