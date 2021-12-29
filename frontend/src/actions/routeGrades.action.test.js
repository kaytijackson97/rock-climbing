import { populateRouteGrades } from './routeGrades.action';
import types from './routeGrades.action';

describe('route grade actions', () => {
    test('populateRouteGrades', () => {
        const routeGrades = [
            {
                routeGradeId: 1,
                gradingSystem: 'BOULDERING',
            },
            {
                routeGradeId: 2,
                gradingSystem: 'BOULDERING',
            },
            {
                routeGradeId: 3,
                gradingSystem: 'BOULDERING',
            },
        ];

        const expected = {
            type: types.POPULATE_ROUTE_GRADES,
            payload: routeGrades,
        };
        expect(populateRouteGrades(routeGrades)).toEqual(expected);
    });
});