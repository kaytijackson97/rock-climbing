import { useSelector } from "react-redux";
import { Link } from 'react-router-dom';
import Climb from "./Climb";
import { CLIENT_ENDPOINTS } from '../../constants/Routes';

function MyClimbs() {
    const { ADD_CLIMB } = CLIENT_ENDPOINTS;

    const climbs = useSelector((state) => state.climbers[0]?.climbs);

    return (
        <div>
            <h1>My Climbs</h1>
            <table className="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">Route Id</th>
                        <th scope="col">Route Type</th>
                        <th scope="col">Grade</th>
                        <th scope="col">Gym Name</th>
                        <th scope="col">Attempts</th>
                    </tr>
                </thead>
                <tbody>
                    {climbs ? climbs.map(c => (<Climb key={c.routeId} climb={c} />)) : {}}
                </tbody>
            </table>
            <button type="button" className="btn btn-primary">
                <Link to={`${ADD_CLIMB}`} className="text-white text-decoration-none">
                    Add Climb
                </Link>
            </button>
        </div>
    );
}

export default MyClimbs;