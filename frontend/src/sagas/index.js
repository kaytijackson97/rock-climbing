import * as climbSaga from './climbSaga';
import * as addClimbSaga from './addClimbSaga';
import * as climberSaga from './climberSaga';
import * as gymSaga from './gymSaga';
import * as routeGradeSaga from './routeGradeSaga';

export function initSagas(sagaMiddleware){
    //runs generator functions from entriesSaga
    Object.values(climbSaga).forEach(sagaMiddleware.run.bind(sagaMiddleware));
    Object.values(addClimbSaga).forEach(sagaMiddleware.run.bind(sagaMiddleware));
    Object.values(climberSaga).forEach(sagaMiddleware.run.bind(sagaMiddleware));
    Object.values(gymSaga).forEach(sagaMiddleware.run.bind(sagaMiddleware));
    Object.values(routeGradeSaga).forEach(sagaMiddleware.run.bind(sagaMiddleware));
}