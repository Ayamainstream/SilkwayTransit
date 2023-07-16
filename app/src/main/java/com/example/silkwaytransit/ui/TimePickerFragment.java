package com.example.silkwaytransit.ui;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    private String timeType;



    public interface TimePickerDialogFragmentEvents{
        void onTimeSelected(String timeType, String time);
    }

    TimePickerDialogFragmentEvents events;

    public void setEvents(TimePickerDialogFragmentEvents events){
        this.events = events;
    }

    public TimePickerFragment(String timeType){
        this.timeType = timeType;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String time = String.valueOf(hourOfDay)+ ":" + String.valueOf(minute);
        events.onTimeSelected(this.timeType, time);

    }

}
