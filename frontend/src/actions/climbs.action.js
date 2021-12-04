const types = {
    GET_CLIMBS: 'GET_CLIMBS',
    ADD_CLIMB: 'ADD_CLIMB',
    UPDATE_CLIMB: 'UPDATE_CLIMB',
    DELETE_CLIMB: 'DELETE_CLIMB',
    POPULATE_CLIMBS: 'POPULATE_CLIMBS',
}

export default types;

export const getAllClimbs = () => {
    return {
        type: types.GET_CLIMBS
    };
}

export const addClimb = (climb) => {
    return {
        type: types.ADD_CLIMB,
        payload: climb,
    };
}

export const updateClimb = (id, climb) => {
    return {
        type: types.UPDATE_CLIMB,
        payload: { id, climb },
    };
}

export const deleteClimb = (id) => {
    return {
        type: types.DELETE_CLIMB,
        payload: id,
    };
}

export const populateClimbs = (climbs) => {
    return { 
        type: types.POPULATE_CLIMBS, 
        payload: climbs
    };
}