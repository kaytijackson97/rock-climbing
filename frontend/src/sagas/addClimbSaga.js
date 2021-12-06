import { put, takeLatest } from 'redux-saga/effects';
import types from '../actions/climbs.action';
import { API_ENDPOINTS } from '../constants/Routes';

export function* addClimbSaga() {
    yield takeLatest(types.ADD_CLIMB, addClimb);
}

function* addClimb(climb) {
    const { FETCH_CLIMB } = API_ENDPOINTS;
    let newClimb;

    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(climb),
        };

    yield fetch(`${process.env.REACT_APP_API_URL}/${FETCH_CLIMB}`, init)
    .then(response => {
        if (response.status !== 201) {
            return Promise.reject("Add climb failed.");
        }
        return response.json();
    })
    .then(json => {newClimb = json})
    .catch(console.log);

    yield put(addClimb(newClimb));
}