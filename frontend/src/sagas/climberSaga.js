import { put } from 'redux-saga/effects';
import { API_ENDPOINTS } from '../constants/Routes';
import { populateClimbers } from '../store/actions/climbers.action';

export function* getAllClimbers() {
    const { FETCH_CLIMBER } = API_ENDPOINTS;

    let climbers;

    yield fetch(`${process.env.REACT_APP_API_URL}/${FETCH_CLIMBER}`)
    .then(response => {
        if (response.status !== 200) {
            return Promise.reject("Get all climbers failed.");
        }
        return response.json();
    })
    .then(json => {climbers = json})
    .catch(console.log);

    yield put(populateClimbers(climbers));
}
