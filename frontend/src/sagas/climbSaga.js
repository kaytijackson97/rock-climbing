import { put, takeLatest } from 'redux-saga/effects';
import { API_ENDPOINTS } from '../constants/Routes';
import { populateClimbs } from '../store/actions/climbs.action';

import types from '../store/actions/climbs.action';

function* getAllClimbs() {
    const { FETCH_ROUTE } = API_ENDPOINTS;
    let climbs;

    yield fetch(`${process.env.REACT_APP_API_URL}/${FETCH_ROUTE}`)
    .then(response => {
        if (response.status !== 200) {
            return Promise.reject("Get all climbs failed.");
        }
        return response.json();
    })
    .then(json => {climbs = json})
    .catch(console.log);

    yield put(populateClimbs(climbs));
}

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

function* deleteClimb({ id, climber }) {
    const { FETCH_ROUTE } = API_ENDPOINTS;

    const init = {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(id),
    };

    yield fetch(`${process.env.REACT_APP_API_URL}/${FETCH_ROUTE}/${id}`, init)
    .then(response => {
        if (response.status !== 204) {
            return Promise.reject("Delete climb failed.");
        }
        return response.json();
    })
    .catch(console.log);

    // update redux store
    const { climbs } = climber;
    const newClimbs = climbs.filter(climb => climb.routeId !== id);

    const newClimber = {
        ...climber,
        climbs: newClimbs,
    }

    yield put({ type: 'DELETE_CLIMB_RESULT', id });
    yield put({ type: 'UPDATE_CLIMBER', payload: { id: climber.climberId, climber: newClimber }});
    yield put({ type: 'UPDATE_CURRENT_CLIMBER', climber: newClimber });
};

function* updateClimb({ id, climb, climber }) {
    const { FETCH_ROUTE } = API_ENDPOINTS;

    const init = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(climb),
    };

    yield fetch(`${process.env.REACT_APP_API_URL}/${FETCH_ROUTE}/${id}`, init)
    .then(response => {
        if (response.status !== 200) {
            return Promise.reject("Update climb failed.");
        }
        return response.json();
    })
    .catch(console.log);

    // update redux store
    let newClimbs = [...climber.climbs];
    const index = climber.climbs.findIndex(c => c.routeId === id);
    newClimbs[index] = { ...newClimbs[index], climb };

    const newClimber = {
        ...climber,
        climbs: newClimbs,
    }

    yield put({ type: 'UPDATE_CLIMB_RESULT', id: climb.routeId, climb });
    yield put({ type: 'UPDATE_CLIMBER', payload: { id: climber.climberId, climber: newClimber }});
}

export function* climbSaga() {
    yield takeLatest(types.GET_CLIMBS, getAllClimbs);
    yield takeLatest(types.ADD_CLIMB, addClimb);
    yield takeLatest(types.DELETE_CLIMB, deleteClimb);
    yield takeLatest(types.UPDATE_CLIMB, updateClimb);
}
