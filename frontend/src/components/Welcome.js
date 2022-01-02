import { useHistory } from 'react-router-dom';

import { CLIENT_ENDPOINTS } from '../constants/Routes';

function Welcome() {
    const history = useHistory();
    const { CLIMBERS } = CLIENT_ENDPOINTS;

    return (
        <div>
            <h1>Rock Climbing</h1>
            <button type="button" className="btn btn-primary" onClick={() => history.push(CLIMBERS)} >
                Select a Climber
            </button>
        </div>
    );
};

export default Welcome;