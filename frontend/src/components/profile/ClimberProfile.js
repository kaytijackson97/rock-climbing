import { useDispatch, useSelector } from "react-redux";
import { Link } from 'react-router-dom';

import { CLIENT_ENDPOINTS } from '../../constants/Routes';

function ClimberProfile() {
    const dispatch = useDispatch();

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
            <button type='button' className='btn btn-primary'>
                <Link to={`${CLIMBERS}`} className="text-white text-decoration-none">
                    Switch Climber
                </Link>
            </button>
        </div>
    );
}

export default ClimberProfile;