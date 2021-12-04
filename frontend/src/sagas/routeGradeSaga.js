import { put, take } from 'redux-saga/effects';
import { API_ENDPOINTS } from '../constants/Routes';
import { populateRouteGrades } from '../actions/routeGrades.action';

export function* getAllRouteGrades() {
    const { FETCH_ROUTE_GRADE } = API_ENDPOINTS;
    let routeGrades;

    yield take(getAllRouteGrades);
    yield fetch(`${process.env.REACT_APP_API_URL}/${FETCH_ROUTE_GRADE}`)
    .then(response => {
        if (response.status !== 200) {
            return Promise.reject("Get all route grades failed.");
        }
        return response.json();
    })
    .then(json => {routeGrades = json})
    .catch(console.log);

    yield put(populateRouteGrades(routeGrades));
};