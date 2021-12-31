import types from '../actions/climbs.action';

const reducer = (state = initialClimbs, action) => {
    let newClimbs = state;
    
    switch(action.type) {
        case types.POPULATE_CLIMBS:
            return action.payload || [];

        case types.ADD_CLIMB_RESULT:
            newClimbs = state.concat({ ...action.payload });
            return newClimbs;

        case types.DELETE_CLIMB_RESULT:
            newClimbs = state.filter(climb => climb.routeId !== action.id);
            return newClimbs;

        case types.UPDATE_CLIMB_RESULT:
            newClimbs = [...state];
            const index = state.findIndex((climb) => climb.routeId === action.id);
            newClimbs[index] = { ...newClimbs[index], ...action.climb };
            return newClimbs;

        default:
            return state;
    }
};

export default reducer;

let initialClimbs = [];