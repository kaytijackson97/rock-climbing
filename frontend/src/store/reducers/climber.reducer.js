import types from '../actions/climbers.action';

const reducer = (state = initialClimbers, action) => {
    let newClimbers = state;
    let index = 0;
    
    switch(action.type) {
        case types.POPULATE_CLIMBERS:
            return action.payload || [];

        case types.ADD_CLIMBER_RESULT:
            newClimbers = state.concat({ ...action.payload });
            return newClimbers;

        case types.ADD_CLIMB_TO_CLIMBER:
            newClimbers = [...state];
            index = state.findIndex((climber) => climber.climberId === action.payload.id);
            const climbs = newClimbers[index].climbs.concat({ ...action.payload.newClimb });
            newClimbers[index].climbs = climbs;
            return newClimbers;

        case types.DELETE_CLIMBER:
            newClimbers = state.filter(climber => climber.climberId !== action.id);
            return newClimbers;

        case types.UPDATE_CLIMBER:
            newClimbers = [...state];
            index = state.findIndex((climber) => climber.climberId === action.id);
            newClimbers[index] = { ...newClimbers[index], ...action.climber };
            return newClimbers;

        default:
            return state;
    }
};

export default reducer;

let initialClimbers = [];