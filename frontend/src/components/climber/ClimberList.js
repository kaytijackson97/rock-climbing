import { useSelector } from "react-redux";
import { Link } from 'react-router-dom';
import { CLIENT_ENDPOINTS } from '../../constants/Routes';
import Climber from "./Climber";

function ClimberList() {
    const { ADD_CLIMBER } = CLIENT_ENDPOINTS;

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
                    {climbers ? climbers.map(c => (<Climber key={c.routeId} climber={c} />)) : {}}
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