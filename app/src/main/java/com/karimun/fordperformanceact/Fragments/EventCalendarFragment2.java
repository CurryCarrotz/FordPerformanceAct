package com.karimun.fordperformanceact.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.karimun.fordperformanceact.Adapters.TimePickerAdapter;
import com.karimun.fordperformanceact.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EventCalendarFragment2 extends Fragment {

    AlertDialog createEventWindow;
    public static EditText timeStartField;
    public static EditText timeEndField;
    public static boolean isTimeStartField = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_calendar2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView addEvent = view.findViewById(R.id.add_event);

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getContext() != null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setView(R.layout.create_event_window);

                    createEventWindow = builder.create();
                    createEventWindow.show();

                    final CheckBox allDayCheckBox = createEventWindow.findViewById(R.id.all_day_checkbox);
                    CheckBox sendNotificationCheckBox = createEventWindow.findViewById(R.id.send_notification_checkbox);
                    final LinearLayout timeEndWrapper = createEventWindow.findViewById(R.id.time_end_wrapper);
                    final EditText dateStartField = createEventWindow.findViewById(R.id.date_start_field);
                    timeStartField = createEventWindow.findViewById(R.id.time_start_field);
                    final EditText dateEndField = createEventWindow.findViewById(R.id.date_end_field);
                    timeEndField = createEventWindow.findViewById(R.id.time_end_field);

                    if (allDayCheckBox != null) {

                        allDayCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                if (allDayCheckBox.isChecked() && timeEndWrapper != null && timeStartField != null && dateEndField != null) {

                                    timeStartField.setVisibility(View.GONE);
                                    timeEndWrapper.setVisibility(View.GONE);
                                    timeStartField.setText("");
                                    dateEndField.setText("");
                                    timeEndField.setText("");
                                } else if (!allDayCheckBox.isChecked() && timeEndWrapper != null) {

                                    timeStartField.setVisibility(View.VISIBLE);
                                    timeEndWrapper.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                    }

                    if (dateStartField != null) {

                        dateStartField.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                pickDate(dateStartField);
                            }
                        });
                    }

                    if (timeStartField != null) {

                        timeStartField.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                isTimeStartField = true;

                                if (getFragmentManager() != null) {
                                    TimePickerAdapter timeStartPicker = new TimePickerAdapter();
                                    timeStartPicker.show(getFragmentManager(), "timePicker");
                                }
                            }
                        });
                    }

                    if (dateEndField != null) {

                        dateEndField.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                pickDate(dateEndField);
                            }
                        });
                    }

                    if (timeEndField != null) {

                        timeEndField.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                isTimeStartField = false;

                                if (getFragmentManager() != null) {
                                    TimePickerAdapter timeEndPicker = new TimePickerAdapter();
                                    timeEndPicker.show(getFragmentManager(), "timePicker");
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    private void pickDate(final EditText startDateInput) {

        final Calendar c = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
                startDateInput.setText(dateFormat.format(c.getTime()));
            }
        };


        if (getContext() != null) {

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

            new DatePickerDialog(getContext(), R.style.MyDatePickerDialog, onDateSetListener, year, month, dayOfMonth).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (createEventWindow != null)
        createEventWindow.dismiss();
    }
}
