function DropDown({ dropDownLabel, selectId, onChange, defaultOption, options }) {
    return (
        <div className="form-group">
            <label className="form-label mt-4">{dropDownLabel}</label>
            <div>
                <select id={selectId} onChange={onChange}>
                    <option>{defaultOption}</option>
                    {options}
                </select>
            </div>
        </div>
    );
}

export default DropDown;