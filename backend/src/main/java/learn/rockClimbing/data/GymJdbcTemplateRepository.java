package learn.rockClimbing.data;

import learn.rockClimbing.data.mappers.GymMapper;
import learn.rockClimbing.models.Gym;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class GymJdbcTemplateRepository implements GymRepository {
    private final JdbcTemplate jdbcTemplate;

    public GymJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Gym> findAll() {
        final String sql = "select gym_name, city, state from gym";
        return jdbcTemplate.query(sql, new GymMapper());
    }

    @Override
    public List<Gym> findGymsByClimberId(int climberId) {
        final String sql = "select g.gym_name, g.city, g.state from gym g" +
                "inner join climber_gym cg on cg.gym_id = g.gym_id " +
                "inner join climber c on c.climber_id = cg.climber_id " +
                "where c.climber_id = ?;";
        return jdbcTemplate.query(sql, new GymMapper(), climberId);
    }

    @Override
    public Gym findById(int gymId) {
        final String sql = "select gym_name, city, state from gym where gym_id = ?;";
        return jdbcTemplate.query(sql, new GymMapper(), gymId).stream().findFirst().orElse(null);
    }

    @Override
    public Gym addGym(Gym gym) {
        if (gym == null) {
            return null;
        }

        final String sql = "insert into gym (gym_name, city, state) values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, gym.getName());
            ps.setString(2, gym.getCity());
            ps.setString(3, gym.getState());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        gym.setGymId(keyHolder.getKey().intValue());
        return gym;
    }

    @Override
    public boolean editGym(Gym gym) {
        if (gym == null) {
            return false;
        }

        final String sql = "update gym set " +
            "gym_name = ?, " +
            "city = ?, " +
            "state = ? " +
            "where gym_id = ?;";

        return jdbcTemplate.update(sql,
                gym.getName(),
                gym.getCity(),
                gym.getState(),
                gym.getGymId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int gymId) {
        jdbcTemplate.update("set sql_safe_updates = 0;");
        // delete routes before gym

        final String deleteRouteSql = "delete from route where gym_id = ?;";
        jdbcTemplate.update(deleteRouteSql, gymId);

        jdbcTemplate.update("set sql_safe_updates = 1;");

        // delete gym
        final String deleteGymSql = "delete from gym where gym_id = ?;";
        return jdbcTemplate.update(deleteRouteSql, gymId) > 0;
    }
}
