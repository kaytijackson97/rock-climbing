import { put, takeLatest } from 'redux-saga/effects';
import types from '../../store/actions/climbs.action';
import { API_ENDPOINTS } from '../../constants/Routes';

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

export function* updateClimbSaga() {
    yield takeLatest(types.UPDATE_CLIMB, updateClimb);
}