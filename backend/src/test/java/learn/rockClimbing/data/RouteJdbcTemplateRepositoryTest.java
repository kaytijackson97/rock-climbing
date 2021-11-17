package learn.rockClimbing.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RouteJdbcTemplateRepositoryTest {

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setUp() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {

    }

    @Test
    void shouldFindRoutesByGym() {

    }

    @Test
    void shouldFindRoutesByClimber() {

    }

    @Test
    void shouldFindRoutesByGymAndClimber() {

    }

    @Test
    void shouldFindRouteByIdIfValidId() {

    }

    @Test
    void shouldNotFindByIdIfInvalidId() {

    }

    @Test
    void shouldAddValidRoute() {

    }

    @Test
    void shouldNotAddNullRoute() {

    }

    @Test
    void shouldNotAddIfNullFields() {

    }

    @Test
    void shouldEditRouteIfValid() {

    }

    @Test
    void shouldNotEditIfInvalidRouteId() {

    }

    @Test
    void shouldNotEditIfNullFields() {

    }

    @Test
    void shouldDeleteIfValidId() {
        assertTrue(routeRepository.deleteRouteById(3));
    }

    @Test
    void shouldNotDeleteIfInvalidId() {
        assertFalse(routeRepository.deleteRouteById(4));
    }
}
