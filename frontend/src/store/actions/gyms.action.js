const types = {
    GET_GYMS: 'GET_GYMS',
    ADD_GYM: 'ADD_GYM',
    UPDATE_GYM: 'UPDATE_GYM',
    DELETE_GYM: 'DELETE_GYM',
    POPULATE_GYMS: 'POPULATE_GYMS',
}

export default types;

export const getAllGyms = () => {
    return {
        type: types.GET_GYMS,
    };
}

export const addGym = ({ gym }) => {
    return {
        type: types.ADD_GYM,
        gym,
    };
}

export const updateGym = ({ id, gym }) => {
    return {
        type: types.UPDATE_GYM,
        id,
        gym,
    };
}

export const deleteGym = ({ id }) => {
    return {
        type: types.DELETE_GYM,
        id,
    };
}

export const populateGyms = (gyms) => {
    return { 
        type: types.POPULATE_GYMS, 
        payload: gyms,
    };
}