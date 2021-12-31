import { put, takeLatest } from 'redux-saga/effects';
import types from '../../store/actions/climbs.action';
import { API_ENDPOINTS } from '../../constants/Routes';

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
};

export function* deleteClimbSaga() {
    yield takeLatest(types.DELETE_CLIMB, deleteClimb);
};