function Climb({ climb }) {
    return(
        <tr key={climb.routeId}>
            <td>{climb.routeId}</td>
            <td>{climb.routeType}</td>
            <td>{climb.routeGrade.gradeLevel}</td>
            <td>{climb.gym.name}</td>
            <td>{climb.attempts}</td>
        </tr>
    );
}

export default Climb;