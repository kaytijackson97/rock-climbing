// TODO: setup setDate
// TODO: figure out why enum isn't working with fetch call

import { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link, useHistory } from 'react-router-dom';
import _find from 'lodash/find';
import _filter from 'lodash/filter';

import { API_ENDPOINTS, CLIENT_ENDPOINTS } from '../../constants/Routes';

// Actions
// import { addClimb } from '../../actions/climbs.action';

function AddClimb() {
    const { MY_CLIMBS } = CLIENT_ENDPOINTS;
    const { FETCH_CLIMB } = API_ENDPOINTS
    const history = useHistory();
    const dispatch = useDispatch();

    const gyms = useSelector(state => state.gyms);
    const routeGrades = useSelector(state => state.routeGrades);

    const [gym, setGym] = useState({});
    const [routeType, setRouteType] = useState("");
    const [routeGrade, setRouteGrade] = useState({});
    const [attempts, setAttempts] = useState(0);
    const [setDate, setSetDate] = useState(null);

    function changeGym() {
        const chosenGym = document.getElementById("gymChoice").value;
        setGym(_find(gyms, {'name': chosenGym}));
    }

    function changeRouteType() {
        const chosenRouteType = document.getElementById("routeTypeChoice").value;

        if (chosenRouteType === "BOULDERING") {
            const boulderingRouteGrades = _filter(routeGrades, { 'gradingSystem': 'BOULDERING' })
            setRouteGrade(boulderingRouteGrades);
        } else if (chosenRouteType === "TOP_ROPE" || "LEAD") {
            const yosemiteRouteGrades = _filter(routeGrades, { 'gradingSystem': 'YOSEMITE' })
            setRouteGrade(yosemiteRouteGrades);
        }

        setRouteType(chosenRouteType);
    }

    function changeRouteGrade() {
        const chosenRouteGrade = document.getElementById("routeGradeChoice").value;
        setRouteGrade(_find(routeGrade, {'gradeLevel': chosenRouteGrade}));
    }

    async function handleSubmit(event) {
        event.preventDefault();
        event.stopPropagation();


        //TODO: move this post call into a saga
        const init = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                gym,
                routeType,
                routeGrade,
                attempts,
                setDate: null,
            }),
        };
    
        await fetch(`${process.env.REACT_APP_API_URL}/${FETCH_CLIMB}`, init)
        .then(response => {
            if (response.status !== 201) {
                return Promise.reject("Add climb failed.");
            }
            return response.json();
        })
        .catch(console.log);

        // dispatch(addClimb({
        //     gym,
        //     routeType,
        //     routeGrade,
        //     attempts,
        //     setDate,
        // }));
        history.push(MY_CLIMBS);
    }

    return (
        <div className="card">
            <h2 className="card-title">Add Climb</h2>
            <div className="card-body">
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label className="form-label mt-4">Gym</label>
                        <select id="gymChoice" onChange={changeGym}>
                            <option selected="selected">Chose a Gym</option>
                            {gyms.map(g => <option key={g.gymId} >{g.name}</option>)}
                        </select>
                    </div>
                    <div className="form-group">
                        <label className="form-label mt-4">Route Type</label>
                        <select id="routeTypeChoice" onChange={changeRouteType}>
                            <option selected="selected">Chose a Route Type</option>
                            <option>BOULDERING</option>
                            <option>TOP_ROPE</option>
                            <option>LEAD</option>
                        </select>
                    </div>
                    <div className="form-group">
                        <label className="form-label mt-4">Grade</label>
                        <select id="routeGradeChoice" onChange={changeRouteGrade}>
                            <option selected="selected">Chose a Grade</option>
                            {routeGrades.map(rg => <option key={rg.routeGradeId}>{rg.gradeLevel}</option>)}
                        </select>
                    </div>
                    <button type="submit" className="btn btn-primary mt-3 mr-3">Submit</button>
                    <Link to={"/api/agency"}>
                        <button type="button" className="btn btn-primary mt-3 ml-3">Cancel</button>
                    </Link>
                </form>
            </div>
        </div>
    );
}

export default AddClimb;