package learn.rockClimbing.data.mappers;

import learn.rockClimbing.models.Gym;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GymMapper implements RowMapper<Gym> {
    @Override
    public Gym mapRow(ResultSet resultSet, int i) throws SQLException {
        Gym gym = new Gym();
        gym.setName(resultSet.getString("gym_name"));
        gym.setCity(resultSet.getString("city"));
        gym.setState(resultSet.getString("state"));

        return gym;
    }
}
