import {
    addClimber,
    updateClimber,
    deleteClimber,
    populateClimbers,
} from './climbers.action';

import types from './climbers.action';

describe('climber actions', () => {
    test('addClimber', () => {
        const climber = { 
            climberId: 1,
            name: 'test',
        };

        const expected = {
            type: types.ADD_CLIMBER,
            climber,
        };
        expect(addClimber({ climber })).toEqual(expected);
    });

    test('updateClimber', () => {
        const id = 1;
        const climber = { 
            climberId: 1,
            name: 'test',
        };

        const expected = {
            type: types.UPDATE_CLIMBER,
            id,
            climber,
        };
        expect(updateClimber({ id, climber })).toEqual(expected);
    });

    test('deleteClimber', () => {
        const id = 1;

        const expected = {
            type: types.DELETE_CLIMBER,
            id,
        };
        expect(deleteClimber({ id })).toEqual(expected);
    });

    test('populateClimbers', () => {
        const climbers = [
            {
                climberId: 1,
                name: 'test',
            },
            {
                climberId: 2,
                name: 'test2',
            },
            {
                climberId: 3,
                name: 'test3',
            }
        ];

        const expected = {
            type: types.POPULATE_CLIMBERS,
            payload: climbers,
        };
        expect(populateClimbers(climbers)).toEqual(expected);
    });
});