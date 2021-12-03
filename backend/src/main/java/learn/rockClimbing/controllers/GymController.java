package learn.rockClimbing.controllers;

import learn.rockClimbing.domain.GymService;
import learn.rockClimbing.domain.Result;
import learn.rockClimbing.domain.ResultType;
import learn.rockClimbing.models.Gym;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/gym")
public class GymController {
    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @GetMapping
    public List<Gym> findAllGyms() {
        return gymService.findAllGyms();
    }

    @GetMapping("/climber/{climberId")
    public ResponseEntity<Object> findAllGymsByClimberId(@PathVariable int climberId) {
        List<Gym> gyms = gymService.findGymsByClimberId(climberId);
        if (gyms.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(gyms, HttpStatus.OK);
    }

    @GetMapping("/{gymId}")
    public ResponseEntity<Object> findGymById(@PathVariable int gymId) {
        Gym gym = gymService.findGymById(gymId);
        if (gym == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gym, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> addGym(@RequestBody @Valid Gym gym, BindingResult results) {
        if (results.hasErrors()) {
            return new ResponseEntity<>(results.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Result<Gym> result = gymService.addGym(gym);
        if (result.getType() != ResultType.SUCCESS) {
            return ErrorResponse.build(result);
        }

        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PostMapping("/{gymId}")
    public ResponseEntity<Object> editGym(@PathVariable int gymId, @RequestBody @Valid Gym gym,
                                          BindingResult results) {
        if (gym.getGymId() != gymId) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (results.hasErrors()) {
            return new ResponseEntity<>(results.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Result<Gym> result = gymService.editGym(gym);
        if (result.getType() != ResultType.SUCCESS) {
            return ErrorResponse.build(result);
        }

        return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
    }

    @DeleteMapping("/{gymId}")
    public ResponseEntity<Object> deleteGymById(@PathVariable @Valid int gymId) {
        if (gymService.deleteGymById(gymId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
