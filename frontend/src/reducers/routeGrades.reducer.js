import types from '../actions/routeGrades.action';

const reducer = (state = initialRouteGrades, action) => {
    switch(action.type) {
        case types.POPULATE_ROUTE_GRADES:
            return action.payload || [];

        default:
            return state;
    }
};

export default reducer;

let initialRouteGrades = [];