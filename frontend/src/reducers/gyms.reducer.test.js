import types from '../actions/gyms.action';
import reducer from './gyms.reducer';

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

    test('populate gyms with payload', () => {
        const state = {
            sKey: 'stateKey',
        };

        const action = {
            type: types.POPULATE_GYMS,
            payload: [
                {
                    gymId: 1,
                    name: 'Test name 1',
                },
                {
                    gymId: 2,
                    name: 'Test name 2',
                },
                {
                    gymId: 3,
                    name: 'Test name 3',
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
            type: types.POPULATE_GYMS,
        };

        expect(reducer(state, action)).toEqual([]);
    });

    test('add gym result', () => {
        const state = [
            {
                gymId: 1,
                name: 'Test name 1',
            },
            {
                gymId: 2,
                name: 'Test name 2',
            },
        ]

        const action = {
            type: types.ADD_GYM_RESULT,
            payload: {
                gymId: 3,
                name: 'Test name 3',
            },
        };

        const expected = [
            {
                gymId: 1,
                name: 'Test name 1',
            },
            {
                gymId: 2,
                name: 'Test name 2',
            },
            {
                gymId: 3,
                name: 'Test name 3',
            },
        ];

        expect(reducer(state, action)).toEqual(expected);
    });

    test('delete gym', () => {
        const state = [
            {
                gymId: 1,
                name: 'Test name 1',
            },
            {
                gymId: 2,
                name: 'Test name 2',
            },
        ]

        const action = {
            type: types.DELETE_GYM,
            id: 2,
        };

        const expected = [
            {
                gymId: 1,
                name: 'Test name 1',
            },
        ];

        expect(reducer(state, action)).toEqual(expected);
    });

    test('update gym', () => {
        const state = [
            {
                gymId: 1,
                name: 'Test name 1',
            },
            {
                gymId: 2,
                name: 'Test name 2',
            },
        ]

        const action = {
            type: types.UPDATE_GYM,
            id: 2,
            gym: {
                gymId: 2,
                name: 'New test name',
            },
        };

        const expected = [
            {
                gymId: 1,
                name: 'Test name 1',
            },
            {
                gymId: 2,
                name: 'New test name',
            },
        ];

        expect(reducer(state, action)).toEqual(expected);
    });
});
