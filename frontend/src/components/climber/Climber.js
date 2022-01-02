import { useDispatch } from "react-redux";
import { useHistory } from "react-router-dom";
import { changeCurrentClimber } from '../../store/actions/currentClimber.action';

import { CLIENT_ENDPOINTS } from '../../constants/Routes';

function Climber({ climber }) {
    const dispatch = useDispatch();
    const history = useHistory();
    
    const { CLIMBER_PROFILE } = CLIENT_ENDPOINTS;

    function handleSelect(climber) {
        dispatch(changeCurrentClimber({ climber }));
        history.push(CLIMBER_PROFILE);
    };

    return (
        <tr key={climber.climberId}>
            <td>{climber.climberId}</td>
            <td>{climber.name}</td>
            <td>{climber.age}</td>
            <td>{climber.monthsClimbing}</td>
            <td>
                <button className="btn btn-primary" onClick={() => handleSelect(climber)}>Select</button>
            </td>
        </tr>
    );
}

export default Climber;