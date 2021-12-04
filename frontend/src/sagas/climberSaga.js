import { put, take } from 'redux-saga/effects';
import { API_ENDPOINTS } from '../constants/Routes';
import { populateClimbers } from '../actions/climbers.action';

export function* getAllClimbers() {
    const { CLIMBER } = API_ENDPOINTS;

    yield take(getAllClimbers);
    const { data } = yield fetch(`${process.env.REACT_APP_API_URL}/${CLIMBER}`)
    .then(response => {
        if (response.status !== 200) {
            return Promise.reject("Get all climbers failed.");
        }
        return response.json();
    })
    .catch(console.log);

    yield put(populateClimbers(data));
}