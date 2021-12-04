import types from '../actions/climbers.action';

const reducer = (state = initialClimbers, action) => {
    let newClimbers = state;
    
    switch(action.type) {
        case types.POPULATE_CLIMBERS:
            return action.payload || [];

        case types.ADD_CLIMBER:
            newClimbers = state.concat({...action.payload});
            return newClimbers;

        case types.DELETE_CLIMBER:
            newClimbers = state.filter(climber => climber.id !== action.payload.id);
            return newClimbers;

        case types.UPDATE_CLIMBER:
            newClimbers = [...state];
            const index = state.findIndex((climber) => climber.id === action.payload.id);
            newClimbers[index] = {...newClimbers[index], ...action.payload.climber};
            return newClimbers;

        default:
            return state;
    }
};

export default reducer;

let initialClimbers = [];