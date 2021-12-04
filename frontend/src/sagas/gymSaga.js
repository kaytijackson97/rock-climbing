import { put, take } from 'redux-saga/effects';
import { API_ENDPOINTS } from '../constants/Routes';
import { populateGyms } from '../actions/gyms.action';
import { populateRouteGrades } from '../actions/routeGrades.action';

export function* getAllGyms() {
    const { FETCH_GYM } = API_ENDPOINTS;
    let gyms;

    yield take(getAllGyms);
    yield fetch(`${process.env.REACT_APP_API_URL}/${FETCH_GYM}`)
    .then(response => {
        if (response.status !== 200) {
            return Promise.reject("Get all gyms failed.");
        }
        return response.json();
    })
    .then(json => {gyms = json})
    .catch(console.log);

    yield put(populateGyms(gyms));
};