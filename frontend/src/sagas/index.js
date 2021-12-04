import * as climbSaga from './climbSaga';
import * as climberSaga from './climberSaga';

export function initSagas(sagaMiddleware){
    //runs generator functions from entriesSaga
    Object.values(climbSaga).forEach(sagaMiddleware.run.bind(sagaMiddleware));
    Object.values(climberSaga).forEach(sagaMiddleware.run.bind(sagaMiddleware));
}