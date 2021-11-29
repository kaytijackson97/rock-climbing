package learn.rockClimbing.data;

import learn.rockClimbing.data.mappers.ClimberMapper;
import learn.rockClimbing.models.Climber;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ClimberJdbcTemplateRepository implements ClimberRepository {
    private final JdbcTemplate jdbcTemplate;

    public ClimberJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Climber> findAll() {
        final String sql = "select climber_id, climber_name, climber_age, length_of_time_climbing from climber;";
        return jdbcTemplate.query(sql, new ClimberMapper());
    }

    @Override
    public Climber findByClimberId(int climberId) {
        final String sql = "select climber_id, climber_name, climber_age, length_of_time_climbing from climber " +
                "where climber_id = ?;";
        return jdbcTemplate.query(sql, new ClimberMapper(), climberId).stream().findFirst().orElse(null);
    }

    @Override
    public Climber addClimber(Climber climber) {
        if (climber == null ||
            climber.getName() == null) {
            return null;
        }

        final String sql = "insert into climber (climber_name, climber_age, length_of_time_climbing) values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, climber.getName());
            ps.setInt(2, climber.getAge());
            ps.setInt(3, climber.getMonthsClimbing());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        climber.setClimberId(keyHolder.getKey().intValue());
        return climber;
    }

    @Override
    public boolean editClimber(Climber climber) {
        if (climber == null ||
            climber.getName() == null) {
            return false;
        }

        final String sql = "update climber set " +
                "climber_name = ?, " +
                "climber_age = ?, " +
                "length_of_time_climbing = ? " +
                "where climber_id = ?;";

        return jdbcTemplate.update(sql,
                climber.getName(),
                climber.getAge(),
                climber.getMonthsClimbing(),
                climber.getClimberId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteClimberById(int climberId) {
        jdbcTemplate.update("set sql_safe_updates = 0;");

        final String deleteClimberGymSql = "delete from climber_gym where climber_id = ?;";
        jdbcTemplate.update(deleteClimberGymSql, climberId);

        jdbcTemplate.update("set sql_safe_updates = 1;");

        final String sql = "delete from climber where climber_id = ?;";
        return jdbcTemplate.update(sql, climberId) > 0;
    }
}
