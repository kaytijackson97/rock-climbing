import types from '../actions/gyms.action';

const reducer = (state = initialGyms, action) => {
    let newGyms = state;
    
    switch(action.type) {
        case types.POPULATE_GYMS:
            return action.payload || [];

        case types.ADD_GYM:
            newGyms = state.concat({ ...action.payload });
            return newGyms;

        case types.DELETE_GYM:
            newGyms = state.filter(gym => gym.gymId !== action.payload.id);
            return newGyms;

        case types.UPDATE_GYM:
            newGyms = [...state];
            const index = state.findIndex((gym) => gym.gymId === action.payload.id);
            newGyms[index] = { ...newGyms[index], ...action.payload.gym };
            return newGyms;

        default:
            return state;
    }
};

export default reducer;

let initialGyms = [];