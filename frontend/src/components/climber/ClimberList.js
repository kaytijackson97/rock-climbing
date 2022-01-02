import { useDispatch, useSelector } from "react-redux";
import { Link } from 'react-router-dom';
import Climber from "./Climber";

import { getAllClimbers } from '../../store/actions/climbers.action';

import { CLIENT_ENDPOINTS } from '../../constants/Routes';
import { useEffect } from "react";

function ClimberList() {
    const dispatch = useDispatch();

    const { ADD_CLIMBER } = CLIENT_ENDPOINTS;

    useEffect(() => {
        dispatch(getAllClimbers());
    }, [dispatch]);

    const climbers = useSelector((state) => state.climbers);

    return (
        <div>
            <h1>Climbers</h1>
            <table className="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">Climber Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Age</th>
                        <th scope="col">Months Climbing</th>
                    </tr>
                </thead>
                <tbody>
                    {climbers ? climbers.map(c => (<Climber key={c.climberId} climber={c} />)) : {}}
                </tbody>
            </table>
            <button type="button" className="btn btn-primary">
                <Link to={`${ADD_CLIMBER}`} className="text-white text-decoration-none">
                    Create New Climber
                </Link>
            </button>
        </div>
    );
}
export default ClimberList;