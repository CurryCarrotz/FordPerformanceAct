package com.karimun.fordperformanceact.Adapters;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.karimun.fordperformanceact.Fragments.EventCalendarFragment;
import com.karimun.fordperformanceact.Fragments.EventCalendarFragment2;
import com.karimun.fordperformanceact.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimePickerAdapter extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    Calendar c;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getContext(), R.style.MyTimePickerDialog, this, hour, minute, DateFormat.is24HourFormat(getContext()));
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);

        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());

        if (EventCalendarFragment.isTimeStartField) {
            if (EventCalendarFragment.timeStartField != null)
            EventCalendarFragment.timeStartField.setText(timeFormat.format(c.getTime()));
        }
        else {
            if (EventCalendarFragment.timeEndField != null)
                EventCalendarFragment.timeEndField.setText(timeFormat.format(c.getTime()));
        }

        if (EventCalendarFragment2.isTimeStartField) {
            if (EventCalendarFragment2.timeStartField != null)
                EventCalendarFragment2.timeStartField.setText(timeFormat.format(c.getTime()));
        }
        else {
            if (EventCalendarFragment2.timeEndField != null)
                EventCalendarFragment2.timeEndField.setText(timeFormat.format(c.getTime()));
        }
    }
}
