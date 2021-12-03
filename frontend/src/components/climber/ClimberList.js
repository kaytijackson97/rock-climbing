import AddClimber from './AddClimber.js';
import Climber from './Climber.js';

function ClimberList({ climbers = [], setClimbers }) {
    return (
        <div>
            <div>
                <AddClimber climbers={climbers} setClimbers={setClimbers} />
            </div>
            <div>
                { climbers.map(c => (<Climber key={c.climberId} climber={c} /> ))}
            </div>
        </div>
    )
}

export default ClimberList;