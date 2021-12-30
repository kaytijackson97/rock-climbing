// TODO: figure out why enum isn't working with fetch call

import { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link, useHistory } from 'react-router-dom';
import { toastr } from 'react-redux-toastr';
import _find from 'lodash/find';
import _filter from 'lodash/filter';

// Constants
import { CLIENT_ENDPOINTS } from '../../constants/Routes';
import { ROCK_CLIMBING_CONSTANTS } from '../../constants/RockClimbingConstants';
import { CLIMB_VALIDATIONS } from '../../constants/Validations';

import CustomDropDown from '../customComponents/CustomDropDown';
import CustomDatePicker from '../customComponents/CustomDatePicker';

// Actions
import { addClimb } from '../../store/actions/climbs.action';

function AddClimb() {
    const { MY_CLIMBS } = CLIENT_ENDPOINTS;
    const { routeTypes, gradeTypes } = ROCK_CLIMBING_CONSTANTS;

    const history = useHistory();
    const dispatch = useDispatch();

    const gyms = useSelector(state => state.gyms);
    const routeGrades = useSelector(state => state.routeGrades);
    const climbers = useSelector(state => state.climbers);

    const [gym, setGym] = useState({});
    const [routeType, setRouteType] = useState("");
    const [routeGrade, setRouteGrade] = useState({});
    const [filteredRouteGrades, setFilteredRouteGrades] = useState(routeGrades);
    const [attempts, setAttempts] = useState(1);
    const [setDate, setSetDate] = useState(new Date());

    const [currentClimber, setCurrentClimber] = useState(climbers[0]);

    // Errors
    const [gymError, setGymError] = useState('');
    const [routeTypeError, setRouteTypeError] = useState('');
    const [gradeError, setGradeError] = useState('');

    function changeGym() {
        const chosenGym = document.getElementById("gymChoice").value;
        const filteredGym = _find(gyms, {'name': chosenGym});

        if (!filteredGym) {
            setGymError(CLIMB_VALIDATIONS.MISSING_GYM);
        } else {
            setGymError('');
        }

        setGym(filteredGym);
    }

    // TODO: route type validation
    function changeRouteType() {
        setFilteredRouteGrades(routeGrades);
        const chosenRouteType = document.getElementById("routeTypeChoice").value;

        if (chosenRouteType === routeTypes[0]) {
            const boulderingRouteGrades = _filter(routeGrades, { 'gradingSystem': routeTypes[0] })
            setFilteredRouteGrades(boulderingRouteGrades);
        } else if (chosenRouteType === routeTypes[1] || chosenRouteType === routeTypes[2]) {
            const yosemiteRouteGrades = _filter(routeGrades, { 'gradingSystem': gradeTypes[1] })
            setFilteredRouteGrades(yosemiteRouteGrades);
        } else {
            setFilteredRouteGrades(routeGrades);
        }

        if (!chosenRouteType) {

        }

        setRouteType(chosenRouteType);
    }

    function changeRouteGrade() {
        const chosenRouteGrade = document.getElementById('routeGradeChoice').value;
        const filteredRouteGrade = _find(routeGrades, {'gradeLevel': chosenRouteGrade});

        if (!filteredRouteGrade) {
            setGradeError(CLIMB_VALIDATIONS.MISSING_GRADE);
        } else {
            setGradeError('');
        }

        setRouteGrade(filteredRouteGrade);
    }

    function handleSubmit(event) {
        event.preventDefault();
        event.stopPropagation();

        // TODO: fix error toastr
        if (gymError.length > 0 || routeTypeError.length > 0 || gradeError.length > 0) {
            toastr.error('Check Invalid Fields.');
            return;
        };

        dispatch(addClimb({
            climb: {
                gym,
                routeType,
                routeGrade,
                attempts,
                setDate,
            },
            climber: currentClimber,
        }));
        history.push(MY_CLIMBS);
    };

    return (
        <div className="card">
            <h2 className="card-title">Add Climb</h2>
            <div className="card-body">
                <form onSubmit={handleSubmit}>
                    <CustomDropDown 
                        dropDownLabel={"Gym"}
                        selectId={"gymChoice"}
                        onChange={changeGym}
                        defaultOption={"Chose a Gym"}
                        options={gyms.map(g => <option key={g.gymId}>{g.name}</option>)}
                        error={gymError}
                    />
                    <CustomDropDown 
                        dropDownLabel={"Route Type"}
                        selectId={"routeTypeChoice"}
                        onChange={changeRouteType}
                        defaultOption={"Chose a Route Type"}
                        //TODO: add key for this guy
                        options={routeTypes.map(rt => <option>{rt}</option>)}
                        error={routeTypeError}
                    />
                    <CustomDropDown
                        dropDownLabel={"Grade"}
                        selectId={"routeGradeChoice"}
                        onChange={changeRouteGrade}
                        defaultOption={"Chose a Grade"}
                        options={filteredRouteGrades.map(rg => <option key={rg.routeGradeId}>{rg.gradeLevel}</option>)}
                        error={gradeError}
                    />
                    <CustomDatePicker 
                        datePickerLabel={"Set Date"}
                        selectedDate={setDate}
                        onChange={date => setSetDate(date)}
                    />
                    <button type="submit" className="btn btn-primary mt-3 mr-3">Submit</button>
                    <Link to={MY_CLIMBS}>
                        <button type="button" className="btn btn-primary mt-3 ml-3">Cancel</button>
                    </Link>
                </form>
            </div>
        </div>
    );
}

export default AddClimb;