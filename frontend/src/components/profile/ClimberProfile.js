import Climber from "../climber/Climber";

function ClimberProfile({ climber }) {
    return (
        <div>
            <h2>My Profile</h2>
            <div>
                <label>Name</label>
                <label>{climber.name}</label>
            </div>
        </div>
    );
}

export default ClimberProfile;