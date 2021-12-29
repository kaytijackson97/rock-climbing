import types from '../actions/climbers.action';
import reducer from './climber.reducer';

describe('climber reducer tests', () => {
    test('default should return given state', () => {
        const state = {
            sKey: 'stateKey',
        };

        const action = {
            type: 'test type',
        };

        expect(reducer(state, action)).toEqual(state);
    });

    test('populate climbers with payload', () => {
        const state = {
            sKey: 'stateKey',
        };

        const action = {
            type: types.POPULATE_CLIMBERS,
            payload: [
                {
                    climberId: 1,
                    name: 'Test name 1',
                },
                {
                    climberId: 2,
                    name: 'Test name 2',
                },
                {
                    climberId: 3,
                    name: 'Test name 3',
                },
            ],
        };

        expect(reducer(state, action)).toEqual(action.payload);
    });

    test('populate climbers with empty array if no payload', () => {
        const state = {
            sKey: 'stateKey',
        };

        const action = {
            type: types.POPULATE_CLIMBERS,
        };

        expect(reducer(state, action)).toEqual([]);
    });

    test('add climber result', () => {
        const state = [
            {
                climberId: 1,
                name: 'Test name 1',
            },
            {
                climberId: 2,
                name: 'Test name 2',
            },
        ]

        const action = {
            type: types.ADD_CLIMBER_RESULT,
            payload: {
                climberId: 3,
                name: 'Test name 3',
            },
        };

        const expected = [
            {
                climberId: 1,
                name: 'Test name 1',
            },
            {
                climberId: 2,
                name: 'Test name 2',
            },
            {
                climberId: 3,
                name: 'Test name 3',
            },
        ];

        expect(reducer(state, action)).toEqual(expected);
    });

    test('delete climber', () => {
        const state = [
            {
                climberId: 1,
                name: 'Test name 1',
            },
            {
                climberId: 2,
                name: 'Test name 2',
            },
        ]

        const action = {
            type: types.DELETE_CLIMBER,
            id: 2,
        };

        const expected = [
            {
                climberId: 1,
                name: 'Test name 1',
            },
        ];

        expect(reducer(state, action)).toEqual(expected);
    });

    test('update climb', () => {
        const state = [
            {
                climberId: 1,
                name: 'Test name 1',
            },
            {
                climberId: 2,
                name: 'Test name 2',
            },
        ]

        const action = {
            type: types.UPDATE_CLIMBER,
            id: 2,
            climber: {
                climberId: 2,
                name: 'New test name',
            },
        };

        const expected = [
            {
                climberId: 1,
                name: 'Test name 1',
            },
            {
                climberId: 2,
                name: 'New test name',
            },
        ];

        expect(reducer(state, action)).toEqual(expected);
    });
});
