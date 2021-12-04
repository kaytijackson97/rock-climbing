import { put, take } from 'redux-saga/effects';
import { API_ENDPOINTS } from '../constants/Routes';
import { populateClimbs } from '../actions/climbs.action';

export function* getAllClimbs() {
    const { CLIMB } = API_ENDPOINTS;

    yield take(getAllClimbs);
    const { data } = yield fetch(`${process.env.REACT_APP_API_URL}/${CLIMB}`)
    .then(response => {
        if (response.status !== 200) {
            return Promise.reject("Get all climbs failed.");
        }
        return response.json();
    })
    .catch(console.log);

    yield put(populateClimbs(data));
}