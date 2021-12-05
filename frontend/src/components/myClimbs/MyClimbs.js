import { useSelector } from "react-redux";
import Climb from "./Climb";

function MyClimbs() {
    const climbs = useSelector((state) => state.climbers[0].climbs);

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
                    {climbs.map(c => (<Climb climb={c} />))}
                </tbody>
            </table>
            <button type="button" className="btn btn-primary">Add Climb</button>
        </div>
    );
}

export default MyClimbs;