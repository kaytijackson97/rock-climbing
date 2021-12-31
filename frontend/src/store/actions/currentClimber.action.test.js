import types, { changeCurrentClimber } from './currentClimber.action';

describe('current climber actions', () => {
    test('changeCurrentClimber', () => {
        const climber = { 
            climberId: 1,
            name: 'test name',
        };

        const expected = {
            type: types.CHANGE_CURRENT_CLIMBER,
            climber,
        };
        expect(changeCurrentClimber({ climber })).toEqual(expected);
    });
});