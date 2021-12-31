import types from '../actions/currentClimber.action';
import reducer from './currentClimber.reducer';

describe('current climber reducer tests', () => {
    test('default should return given state', () => {
        const state = {
            sKey: 'stateKey',
        };

        const action = {
            type: 'test type',
        };

        expect(reducer(state, action)).toEqual(state);
    });

    test('change current climber', () => {
        const state = {
            sKey: 'stateKey',
        };

        const action = {
            type: types.CHANGE_CURRENT_CLIMBER_IN_STORE,
            climber: {
                climberId: 1,
                name: 'test name',
            }
        };

        const expected = {
            climberId: 1,
            name: 'test name',
        }

        expect(reducer(state, action)).toEqual(expected);
    });
})