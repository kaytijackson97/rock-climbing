import types from '../actions/currentClimber.action';

const reducer = (state = initialCurrentClimber, action) => {
    switch(action.type) {
        case types.CHANGE_CURRENT_CLIMBER_IN_STORE:
            return action.climber;

        default:
            return state;
    }
};

export default reducer;

let initialCurrentClimber = {};