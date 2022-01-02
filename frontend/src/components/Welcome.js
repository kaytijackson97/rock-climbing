import { Link } from 'react-router-dom';

import { CLIENT_ENDPOINTS } from '../constants/Routes';

function Welcome() {
    const { CLIMBERS } = CLIENT_ENDPOINTS;

    return (
        <div>
            <h1>Rock Climbing</h1>
            <button type="button" className="btn btn-primary">
                <Link to={`${CLIMBERS}`} className="text-white text-decoration-none">
                    Select a Climber
                </Link>
            </button>
        </div>
    );
};

export default Welcome;