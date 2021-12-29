import {
    addClimb,
    updateClimb,
    deleteClimb,
    populateClimbs,
} from './climbs.action';

import types from './climbs.action';

describe('climb actions', () => {
    test('addClimb', () => {
        const climb = { 
            routeId: 1,
            routeType: 'BOULDERING',
        };

        const expected = {
            type: types.ADD_CLIMB,
            climb,
        };
        expect(addClimb({ climb })).toEqual(expected);
    });

    test('updateClimb', () => {
        const id = 1;
        const climb = { 
            routeId: 1,
            routeType: 'BOULDERING',
        };
        const climber = {
            climberId: 1,
            name: 'test name',
        }

        const expected = {
            type: types.UPDATE_CLIMB,
            id,
            climb,
            climber,
        };
        expect(updateClimb({ id, climb, climber })).toEqual(expected);
    });

    test('deleteClimb', () => {
        const id = 1;

        const expected = {
            type: types.DELETE_CLIMB,
            id,
        };
        expect(deleteClimb({ id })).toEqual(expected);
    });

    test('populateClimbs', () => {
        const climbs = [
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

        const expected = {
            type: types.POPULATE_CLIMBS,
            payload: climbs,
        };
        expect(populateClimbs(climbs)).toEqual(expected);
    });
});