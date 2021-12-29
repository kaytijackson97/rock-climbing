const types = {
    GET_CLIMBS: 'GET_CLIMBS',
    ADD_CLIMB: 'ADD_CLIMB',
    ADD_CLIMB_RESULT: 'ADD_CLIMB_RESULT',
    UPDATE_CLIMB: 'UPDATE_CLIMB',
    UPDATE_CLIMB_RESULT: 'UPDATE_CLIMB_RESULT',
    DELETE_CLIMB: 'DELETE_CLIMB',
    POPULATE_CLIMBS: 'POPULATE_CLIMBS',
}

export default types;

export const getAllClimbs = () => {
    return {
        type: types.GET_CLIMBS,
    };
}

export const addClimb = ({ climb, climber }) => {
    return {
        type: types.ADD_CLIMB,
        climb,
        climber,
    };
}

export const updateClimb = ({ id, climb, climber }) => {
    return {
        type: types.UPDATE_CLIMB,
        id,
        climb,
        climber,
    };
}

export const deleteClimb = ({ id }) => {
    return {
        type: types.DELETE_CLIMB,
        id,
    };
}

export const populateClimbs = (climbs) => {
    return { 
        type: types.POPULATE_CLIMBS, 
        payload: climbs,
    };
}