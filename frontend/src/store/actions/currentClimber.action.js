const types = {
    CHANGE_CURRENT_CLIMBER: 'CHANGE_CURRENT_CLIMBER',
    CHANGE_CURRENT_CLIMBER_IN_STORE: 'CHANGE_CURRENT_CLIMBER_IN_STORE',
}

export default types;

export const changeCurrentClimber = ({ climber }) => {
    return { 
        type: types.CHANGE_CURRENT_CLIMBER, 
        climber,
    };
}