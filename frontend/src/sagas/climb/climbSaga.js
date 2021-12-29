import { put, take } from 'redux-saga/effects';
import { API_ENDPOINTS } from '../../constants/Routes';
import { populateClimbs } from '../../store/actions/climbs.action';

export function* getAllClimbs() {
    const { FETCH_ROUTE } = API_ENDPOINTS;
    let climbs;

    yield take(getAllClimbs);
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
