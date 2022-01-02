import { useSelector } from "react-redux";
import { useHistory } from 'react-router-dom';

import { CLIENT_ENDPOINTS } from '../../constants/Routes';

function ClimberProfile() {
    const history = useHistory();

    const { CLIMBERS } = CLIENT_ENDPOINTS;

    let currentClimber = useSelector((state) => state.currentClimber);

    return (
        <div>
            <h2>My Profile</h2>
            <div className='row'>
                <div className='col'>
                    <label>Name</label>
                    <label>{currentClimber.name}</label>
                </div>
            </div>
            <div className='row'>
                <div className='col'>
                    <label>Age</label>
                    <label>{currentClimber.age}</label>
                </div>
            </div>
            <div className='row'>
                <div className='col'>
                    <label>Months Climbing</label>
                    <label>{currentClimber.monthsClimbing}</label>
                </div>
            </div>
            <button type='button' className='btn btn-primary' onClick={() => history.push(CLIMBERS)}>
                Switch Climber
            </button>
        </div>
    );
}

export default ClimberProfile;