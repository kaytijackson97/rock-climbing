package learn.rockClimbing.controllers;

import learn.rockClimbing.domain.Result;
import learn.rockClimbing.domain.ResultType;
import learn.rockClimbing.domain.RouteService;
import learn.rockClimbing.models.Route;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/route")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public List<Route> findAllRoutes() {
        return routeService.findAllRoutes();
    }

    @GetMapping("/climber/{climberId}")
    public ResponseEntity<Object> findAllRoutesByClimberId(@PathVariable int climberId) {
        List<Route> routes = routeService.findRoutesByClimber(climberId);
        if (routes.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping("/gym/{gymId}")
    public ResponseEntity<Object> findAllRoutesByGym(@PathVariable int gymId) {
        List<Route> routes = routeService.findRoutesByGym(gymId);
        if (routes.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping("/gym/{gymId}/climber/{climberId}")
    public ResponseEntity<Object> findAllRoutesByGymAndClimber (@PathVariable int gymId, @PathVariable int climberId) {
        List<Route> routes = routeService.findRoutesByGymAndClimber(gymId, climberId);
        if (routes.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping("/{routeId}")
    public ResponseEntity<Object> findRouteById(@PathVariable int routeId) {
        Route route = routeService.findRouteById(routeId);
        if (route == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> addRoute(@RequestBody @Valid Route route, BindingResult results) {
        if (results.hasErrors()) {
            return new ResponseEntity<>(results.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Result<Route> result = routeService.addRoute(route);
        if (result.getType() != ResultType.SUCCESS) {
            return ErrorResponse.build(result);
        }

        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PostMapping("/{routeId}")
    public ResponseEntity<Object> editRoute(@PathVariable int routeId, @RequestBody @Valid Route route,
                                          BindingResult results) {
        if (route.getRouteId() != routeId) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (results.hasErrors()) {
            return new ResponseEntity<>(results.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Result<Route> result = routeService.editRoute(route);
        if (result.getType() != ResultType.SUCCESS) {
            return ErrorResponse.build(result);
        }

        return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
    }

    @DeleteMapping("/{routeId}")
    public ResponseEntity<Object> deleteRouteById(@PathVariable @Valid int routeId) {
        if (routeService.deleteRouteById(routeId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
