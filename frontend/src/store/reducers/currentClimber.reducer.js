import types from '../actions/currentClimber.action';

const reducer = (state = initialCurrentClimber, action) => {
    switch(action.type) {
        case types.ADD_CLIMB_TO_CURRENT_CLIMBER:
            let newClimber = [...state];
            const climbs = newClimber.climbs.concat({ ...action.newClimb });
            newClimber.climbs = climbs;
            return newClimber;

        case types.CHANGE_CURRENT_CLIMBER_IN_STORE:
        case types.UPDATE_CURRENT_CLIMBER:
            return action.climber;

        default:
            return state;
    }
};

export default reducer;

let initialCurrentClimber = {};