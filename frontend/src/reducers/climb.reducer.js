import types from '../actions/climbs.action';

const reducer = (state = initialClimbs, action) => {
    let newClimbs = state;
    
    switch(action.type) {
        case types.POPULATE_CLIMBS:
            return action.payload;

        case types.ADD_CLIMB:
            newClimbs = state.concat({...action.payload});
            return newClimbs;

        case types.DELETE_CLIMB:
            newClimbs = state.filter(climb => climb.id !== action.payload.id);
            return newClimbs;

        case types.UPDATE_CLIMB:
            newClimbs = [...state];
            const index = state.findIndex((climb) => climb.id === action.payload.id);
            newClimbs[index] = {...newClimbs[index], ...action.payload.climb};
            return newClimbs;

        default:
            return state;
    }
};

export default reducer;

let initialClimbs = [];