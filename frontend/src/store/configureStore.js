import { applyMiddleware, combineReducers, createStore } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import createSagaMiddleware from 'redux-saga';
import { initSagas } from '../sagas/index';

// Reducers
import climbReducer from '../reducers/climb.reducer';
import climberReducer from '../reducers/climber.reducer';
import gymReducer from '../reducers/gyms.reducer';
import routeGradeReducer from '../reducers/routeGrades.reducer';

const sagaMiddleware = createSagaMiddleware();
const middlewares = [sagaMiddleware];

//Redux store with combined reducer
const configureStore = () => {
    const store = createStore(
        combineReducers({
            climbs: climbReducer,
            climbers: climberReducer,
            gyms: gymReducer,
            routeGrades: routeGradeReducer,
        }), 
        composeWithDevTools(
            applyMiddleware(...middlewares)
        )
    );

    //runs all sagas in the initSaga files
    initSagas(sagaMiddleware);
    return store;
}

export default configureStore;

