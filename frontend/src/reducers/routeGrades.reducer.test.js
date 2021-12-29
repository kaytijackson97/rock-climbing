import types from '../actions/routeGrades.action';
import reducer from './routeGrades.reducer';

describe('gyms reducer tests', () => {
    test('default should return given state', () => {
        const state = {
            sKey: 'stateKey',
        };

        const action = {
            type: 'test type',
        };

        expect(reducer(state, action)).toEqual(state);
    });

    test('populate route grades with payload', () => {
        const state = {
            sKey: 'stateKey',
        };

        const action = {
            type: types.POPULATE_ROUTE_GRADES,
            payload: [
                {
                    routeGradeId: 1,
                    gradingSystem: 'BOULDERING',
                },
                {
                    routeGradeId: 2,
                    name: 'BOULDERING',
                },
                {
                    routeGradeId: 3,
                    name: 'BOULDERING',
                },
            ],
        };

        expect(reducer(state, action)).toEqual(action.payload);
    });

    test('populate gyms with empty array if no payload', () => {
        const state = {
            sKey: 'stateKey',
        };

        const action = {
            type: types.POPULATE_ROUTE_GRADES,
        };

        expect(reducer(state, action)).toEqual([]);
    });
});
