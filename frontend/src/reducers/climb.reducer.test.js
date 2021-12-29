import types from '../actions/climbs.action';
import reducer from './climb.reducer';

describe('climb reducer tests', () => {
    test('default should return given state', () => {
        const state = {
            sKey: 'stateKey',
        };

        const action = {
            type: 'test type',
        };

        expect(reducer(state, action)).toEqual(state);
    });

    test('populate climbs with payload', () => {
        const state = {
            sKey: 'stateKey',
        };

        const action = {
            type: types.POPULATE_CLIMBS,
            payload: [
                {
                    routeId: 1,
                    routeType: 'BOULDERING',
                },
                {
                    routeId: 2,
                    routeType: 'BOULDERING',
                },
                {
                    routeId: 3,
                    routeType: 'BOULDERING',
                },
            ],
        };

        expect(reducer(state, action)).toEqual(action.payload);
    });

    test('populate climbs with empty array if no payload', () => {
        const state = {
            sKey: 'stateKey',
        };

        const action = {
            type: types.POPULATE_CLIMBS,
        };

        expect(reducer(state, action)).toEqual([]);
    });

    test('add climb result', () => {
        const state = [
            {
                routeId: 1,
                routeType: 'BOULDERING',
            },
            {
                routeId: 2,
                routeType: 'BOULDERING',
            },
        ]

        const action = {
            type: types.ADD_CLIMB_RESULT,
            payload: {
                routeId: 3,
                routeType: 'BOULDERING',
            },
        };

        const expected = [
            {
                routeId: 1,
                routeType: 'BOULDERING',
            },
            {
                routeId: 2,
                routeType: 'BOULDERING',
            },
            {
                routeId: 3,
                routeType: 'BOULDERING',
            },
        ];

        expect(reducer(state, action)).toEqual(expected);
    });

    test('delete climb', () => {
        const state = [
            {
                routeId: 1,
                routeType: 'BOULDERING',
            },
            {
                routeId: 2,
                routeType: 'BOULDERING',
            },
        ]

        const action = {
            type: types.DELETE_CLIMB,
            id: 2,
        };

        const expected = [
            {
                routeId: 1,
                routeType: 'BOULDERING',
            },
        ];

        expect(reducer(state, action)).toEqual(expected);
    });

    test('update climb', () => {
        const state = [
            {
                routeId: 1,
                routeType: 'BOULDERING',
            },
            {
                routeId: 2,
                routeType: 'BOULDERING',
            },
        ]

        const action = {
            type: types.UPDATE_CLIMB,
            id: 2,
            climb: {
                routeId: 2,
                routeType: 'TOP_ROPE',
            }
        };

        const expected = [
            {
                routeId: 1,
                routeType: 'BOULDERING',
            },
            {
                routeId: 2,
                routeType: 'TOP_ROPE',
            },
        ];

        expect(reducer(state, action)).toEqual(expected);
    });
});
