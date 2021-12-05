function Climb(climb) {
    return(
        <tr key={climb.climb.routeId}>
            <td>{climb.climb.routeId}</td>
            <td>{climb.climb.routeType}</td>
            <td>{climb.climb.routeGrade.gradeLevel}</td>
            <td>{climb.climb.gym.name}</td>
            <td>{climb.climb.attempts}</td>
        </tr>
    );
}

export default Climb;