import {
    addGym,
    updateGym,
    deleteGym,
    populateGyms,
} from './gyms.action';

import types from './gyms.action';

describe('gym actions', () => {
    test('addGym', () => {
        const gym = { 
            gymId: 1,
            name: 'test',
        };

        const expected = {
            type: types.ADD_GYM,
            gym,
        };
        expect(addGym({ gym })).toEqual(expected);
    });

    test('updateGym', () => {
        const id = 1;
        const gym = { 
            gymId: 1,
            name: 'test',
        };

        const expected = {
            type: types.UPDATE_GYM,
            id,
            gym,
        };
        expect(updateGym({ id, gym })).toEqual(expected);
    });

    test('deleteGym', () => {
        const id = 1;

        const expected = {
            type: types.DELETE_GYM,
            id,
        };
        expect(deleteGym({ id })).toEqual(expected);
    });

    test('populateGyms', () => {
        const gyms = [
            {
                gymId: 1,
                name: 'test1',
            },
            {
                gymId: 2,
                name: 'test2',
            },
            {
                gymId: 3,
                name: 'test3',
            },
        ];

        const expected = {
            type: types.POPULATE_GYMS,
            payload: gyms,
        };
        expect(populateGyms(gyms)).toEqual(expected);
    });
});