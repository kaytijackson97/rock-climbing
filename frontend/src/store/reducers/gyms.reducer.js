import types from '../actions/gyms.action';

const reducer = (state = initialGyms, action) => {
    let newGyms = state;
    
    switch(action.type) {
        case types.POPULATE_GYMS:
            return action.payload || [];

        case types.ADD_GYM_RESULT:
            newGyms = state.concat({ ...action.payload });
            return newGyms;

        case types.DELETE_GYM:
            newGyms = state.filter(gym => gym.gymId !== action.id);
            return newGyms;

        case types.UPDATE_GYM:
            newGyms = [...state];
            const index = state.findIndex((gym) => gym.gymId === action.id);
            newGyms[index] = { ...newGyms[index], ...action.gym };
            return newGyms;

        default:
            return state;
    }
};

export default reducer;

let initialGyms = [];