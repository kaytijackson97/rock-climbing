import { API_ENDPOINTS } from '../../constants/Routes.js';
import ClimberList from './ClimberList.js';

import { useEffect, useState } from "react";

function ClimberApp() {
    const { CLIMBER } = API_ENDPOINTS;

    const [climbers, setClimbers] = useState([]);

    useEffect(() => {
        fetch(`${process.env.REACT_APP_API_URL}/${CLIMBER}`)
        .then(response => {
            if (response.status !== 200) {
                return Promise.reject("Get all climbers failed.");
            }
            return response.json();
        })
        .then(json => {
            console.log(json);
            setClimbers(json);
        })
        .catch(console.log);
    });

    function editClimberByClimberId(climber) {
        const newClimbers = [];

        for (const c of climbers) {
            if (c.climberId !== climber.climberId) {
                newClimbers.push(c);
            } else {
                newClimbers.push(climber);
            }
        }

        setClimbers(newClimbers);
    }

    return (
        <div>
            <h2>Climbers</h2>
            <ClimberList climbers={climbers} setClimbers={setClimbers} editClimberByClimberId={editClimberByClimberId} />
            {console.log('climbers', climbers)}
        </div>
    )
}
export default ClimberApp;