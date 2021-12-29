package learn.rockClimbing.controllers;

import learn.rockClimbing.domain.ClimberService;
import learn.rockClimbing.domain.Result;
import learn.rockClimbing.models.ClimberRoute;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("api/climber/route")
public class ClimberRouteController {
    private final ClimberService service;

    public ClimberRouteController(ClimberService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody ClimberRoute climberRoute) {
        Result<Void> result = service.addRoute(climberRoute);

        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{climberId}/{routeId}")
    public ResponseEntity<Void> deleteByKey(@PathVariable int climberId, @PathVariable int routeId) {
        if (service.deleteRouteByKey(climberId, routeId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
