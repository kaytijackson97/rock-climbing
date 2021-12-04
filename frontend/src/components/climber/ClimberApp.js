import ClimberList from './ClimberList.js';
import { useState } from 'react';

function ClimberApp() {
    const [climbers, setClimbers] = useState([]);

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
            <ClimberList climbers={climbers} setClimbers={setClimbers} />
        </div>
    )
}
export default ClimberApp;