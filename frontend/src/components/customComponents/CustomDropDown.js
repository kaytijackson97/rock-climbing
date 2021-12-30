function DropDown({ dropDownLabel, selectId, onChange, defaultOption, options, error }) {
    if (error.length > 0) {
        document.getElementById(selectId).className += " is-invalid";
    } else {
        // document.getElementById(selectId).className -= " is-invalid";
    };

    return (
        <div className="form-group">
            <label className="form-label mt-4">{dropDownLabel}</label>
            <div>
                <select id={selectId} onChange={onChange} className="form-control">
                    <option>{defaultOption}</option>
                    {options}
                </select>
            </div>
            <span className="validation-error">{error}</span>
        </div>
    );
}

export default DropDown;