export const API_ENDPOINTS = {
    FETCH_CLIMBER: 'api/climber',
    FETCH_GYM: 'api/gym',
    FETCH_GYM_BY_CLIMBER: 'api/gym/climber',
    FETCH_ROUTE: 'api/route',
    FETCH_ROUTE_BY_GYM: 'api/route/gym',
    FETCH_ROUTE_BY_CLIMBER: 'api/route/climber',
    FETCH_ROUTE_BY_GYM_AND_CLIMBER: 'api/route/gym/:gymId/climber/:climberId',
    FETCH_ROUTE_GRADE: 'api/route-grade',
    FETCH_ROUTE_GRADE_BY_GRADING_SYSTEM: 'api/route-grade/grading-system',
    FETCH_CLIMBER_ROUTE: 'api/climber/route',
};

export const CLIENT_ENDPOINTS = {
    MY_CLIMBS: '/my-climbs',
    ADD_CLIMB: '/climb/add',
    CLIMBERS: '/climbers',
    CLIMBER_PROFILE: '/climber',
}
