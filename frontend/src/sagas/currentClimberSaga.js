import { put, takeLatest } from 'redux-saga/effects';
import types from '../store/actions/currentClimber.action';

function* changeCurrentClimber({ climber }) {
    yield put({ type: 'CHANGE_CURRENT_CLIMBER_IN_STORE', climber });
}

export function* currentClimberSaga() {
    yield takeLatest(types.CHANGE_CURRENT_CLIMBER, changeCurrentClimber);
}