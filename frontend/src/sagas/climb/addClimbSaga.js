import { put, takeLatest } from 'redux-saga/effects';
import types from '../../store/actions/climbs.action';
import { API_ENDPOINTS } from '../../constants/Routes';

function* addClimb({ climb, climber }) {
    const { FETCH_ROUTE, FETCH_CLIMBER_ROUTE } = API_ENDPOINTS;
    let newClimb;

    const routeInit = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(climb),
    };

    yield fetch(`${process.env.REACT_APP_API_URL}/${FETCH_ROUTE}`, routeInit)
    .then(response => {
        if (response.status !== 201) {
            return Promise.reject("Add climb failed.");
        }
        return response.json();
    })
    .then(json => {newClimb = json})
    .catch(console.log);

    const climberRouteInit = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            climber,
            route: newClimb,
        }),
    }

    yield fetch(`${process.env.REACT_APP_API_URL}/${FETCH_CLIMBER_ROUTE}`, climberRouteInit)
    .then(response => {
        if (response.status !== 201) {
            return Promise.reject("Add climb to bridge table failed.");
        }
        return response.json();
    })
    .catch(console.log);

    // update redux store
    yield put({ type: 'ADD_CLIMB_RESULT', payload: newClimb });
    yield put({ type: 'ADD_CLIMB_TO_CLIMBER', payload: { id: climber.climberId, newClimb } });
    yield put({ type: 'ADD_CLIMB_TO_CURRENT_CLIMBER', newClimb });
}

export function* addClimbSaga() {
    yield takeLatest(types.ADD_CLIMB, addClimb);
}