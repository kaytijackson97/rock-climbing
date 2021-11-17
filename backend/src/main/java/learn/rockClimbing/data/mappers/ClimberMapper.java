package learn.rockClimbing.data.mappers;

import learn.rockClimbing.models.Climber;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClimberMapper implements RowMapper<Climber> {
    @Override
    public Climber mapRow(ResultSet resultSet, int i) throws SQLException {
        Climber climber = new Climber();
        climber.setName(resultSet.getString("climber_name"));
        climber.setAge(resultSet.getInt("climber_age"));
        climber.setMonthsClimbing(resultSet.getInt("length_of_time_climbing"));

        return climber;
    }
}
