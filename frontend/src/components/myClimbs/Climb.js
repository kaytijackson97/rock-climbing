// TODO: fix add not updating climb table

import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { updateClimb, deleteClimb } from "../../store/actions/climbs.action";

function Climb({ climb }) {
    const currentClimber = useSelector(state => state.currentClimber);

    const [listedClimb, setListedClimb] = useState(climb);
    let [attempts, setAttempts] = useState(climb.attempts);

    const dispatch = useDispatch();

    function handleIncrease() {
        attempts = attempts + 1;
        setAttempts(attempts);
        updateClimbCount();
    };

    function handleDecrease() {
        if (attempts > 1) {
            attempts = attempts - 1;
            setAttempts(attempts);
            updateClimbCount();
        }
    };

    function updateClimbCount() {
        const newClimb = {
            ...listedClimb,
            attempts,
        };

        dispatch(updateClimb({
            id: climb.routeId,
            climb: newClimb,
            climber: currentClimber,
        }));

        setListedClimb(newClimb);
    }

    function handleDelete(id) {
        dispatch(deleteClimb({
            id,
            climber: currentClimber
        }));
    };

    return (
        <tr key={listedClimb.routeId}>
            <td>{listedClimb.routeId}</td>
            <td>{listedClimb.routeType}</td>
            <td>{listedClimb.routeGrade.gradeLevel}</td>
            <td>{listedClimb.gym.name}</td>
            <td>
                <button className="btn btn-primary" onClick={handleIncrease}>+</button>
                <span className="climb-table-spacing">{listedClimb.attempts}</span>
                <button className= "btn btn-primary" onClick={handleDecrease}>-</button>
            </td>
            <td>
                <button className="btn btn-danger" onClick={() => handleDelete(listedClimb.routeId)}>Delete</button>
            </td>
        </tr>
    );
}

export default Climb;