const types = {
    ADD_CLIMBER: 'ADD_CLIMBER',
    ADD_CLIMB_TO_CLIMBER: 'ADD_CLIMB_TO_CLIMBER',
    DELETE_CLIMBER: 'DELETE_CLIMBER',
    GET_CLIMBERS: 'GET_CLIMBERS',
    POPULATE_CLIMBERS: 'POPULATE_CLIMBERS',
    UPDATE_CLIMBER: 'UPDATE_CLIMBER',
}

export default types;

export const getAllClimbers = () => {
    return {
        type: types.GET_CLIMBERS,
    };
}

export const addClimber = ({ climber }) => {
    return {
        type: types.ADD_CLIMBER,
        climber,
    };
}

export const updateClimber = ({ id, climber }) => {
    return {
        type: types.UPDATE_CLIMBER,
        id,
        climber,
    };
}

export const deleteClimber = ({ id }) => {
    return {
        type: types.DELETE_CLIMBER,
        id,
    };
}

export const populateClimbers = (climbers) => {
    return { 
        type: types.POPULATE_CLIMBERS, 
        payload: climbers,
    };
}