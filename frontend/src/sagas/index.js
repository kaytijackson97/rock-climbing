import * as climbSaga from './climbSaga';
import * as climberSaga from './climberSaga';
import * as currentClimberSaga from './currentClimberSaga';
import * as gymSaga from './gymSaga';
import * as routeGradeSaga from './routeGradeSaga';

export function initSagas(sagaMiddleware){
    //runs generator functions from entriesSaga
    Object.values(climbSaga).forEach(sagaMiddleware.run.bind(sagaMiddleware));
    Object.values(climberSaga).forEach(sagaMiddleware.run.bind(sagaMiddleware));
    Object.values(currentClimberSaga).forEach(sagaMiddleware.run.bind(sagaMiddleware));
    Object.values(gymSaga).forEach(sagaMiddleware.run.bind(sagaMiddleware));
    Object.values(routeGradeSaga).forEach(sagaMiddleware.run.bind(sagaMiddleware));
}