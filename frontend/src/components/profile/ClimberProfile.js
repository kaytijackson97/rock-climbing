import { useSelector } from "react-redux";

function ClimberProfile() {
    const climbers = useSelector((state) => state.climbers);
    const climber = climbers[0];

    return (
        <div className='App'>
            <div className='container'>
                <h2>My Profile</h2>
                <div className='row'>
                    <div className='col'>
                        <label>Name</label>
                        <label>{climber.name}</label>
                    </div>
                </div>
                <div className='row'>
                    <div className='col'>
                        <label>Age</label>
                        <label>{climber.age}</label>
                    </div>
                </div>
                <div className='row'>
                    <div className='col'>
                        <label>Months Climbing</label>
                        <label>{climber.monthsClimbing}</label>
                    </div>
                </div>
                <div className='row'>
                    <div className='col'>
                        <label>Hardest Climb</label>
                        {/* <label>{climber.climbs.size()}</label> */}
                    </div>
                </div>
                <div className='row'>
                    <div className='col'>
                        <label>Favorite Gym</label>
                        {/* <label>{climber.gym.size()}</label> */}
                    </div>
                </div>
                <button type='button' className='btn btn-primary'>Switch Climber</button>
            </div>
        </div>
    );
}

export default ClimberProfile;