import { API_ENDPOINTS } from '../../constants/Routes.js';
import { useState } from "react";

function AddClimber({ climbers = [], setClimbers }) {
    const { CLIMBER } = API_ENDPOINTS;

    const [name, setName] = useState("");
    const [age, setAge] = useState(0);
    const [monthsClimbing, setMonthsClimbing] = useState(0);

    function handleAddClimber(event) {
        event.preventDefault();
        event.stopPropagation();

        let climber = {
            name,
            age,
            monthsClimbing,
        };

        addClimber(climber);
    }

    function handleClimberNameChange(event) {
        setName(event.target.value);
    }

    function handleClimberAgeChange(event) {
        setAge(event.target.value);
    }

    function handleClimberMonthsClimbingChange(event) {
        setMonthsClimbing(event.target.value);
    }

    async function addClimber(climber) {
        const init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
            },
        }

        await fetch(`${process.env.REACT_APP_API_URL}/${CLIMBER}`, init)
        .then(response => {
            if (response.status !== 201) {
                return Promise.reject("Add Climber failed.");
            }
            return response.json();
        })
        .then(json => setClimbers([...climbers, json]));
    }

    return (
        <div>
            {/* {climbers} */}
        </div>
    )
}

export default AddClimber;