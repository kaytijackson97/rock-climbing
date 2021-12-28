import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";

function CustomDatePicker({ datePickerLabel, selectedDate, onChange }) {
    return(
        <div className="form-group">
            <label className="form-label mt-4">{datePickerLabel}</label>
            <DatePicker
                selected={selectedDate}
                onChange={onChange}
            />
        </div>
    );
}

export default CustomDatePicker;