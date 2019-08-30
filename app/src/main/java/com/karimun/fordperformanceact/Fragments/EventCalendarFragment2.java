package com.karimun.fordperformanceact.Fragments;

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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.karimun.fordperformanceact.R;

public class EventCalendarFragment2 extends Fragment {

    AlertDialog createEventWindow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(R.layout.create_event_window);

                createEventWindow = builder.create();
                createEventWindow.show();

                final CheckBox allDayCheckBox = createEventWindow.findViewById(R.id.all_day_checkbox);
                CheckBox sendNotificationCheckBox = createEventWindow.findViewById(R.id.send_notification_checkbox);
                final LinearLayout timeEndWrapper = createEventWindow.findViewById(R.id.time_end_wrapper);

                if (allDayCheckBox != null) {
                    allDayCheckBox.setText("All-day");
                    allDayCheckBox.setTextColor(getResources().getColor(R.color.colorGeneralText));

                    allDayCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                            if (allDayCheckBox.isChecked() && timeEndWrapper != null) {

                                timeEndWrapper.setVisibility(View.GONE);
                            }
                            else if (!allDayCheckBox.isChecked() && timeEndWrapper != null) {

                                timeEndWrapper.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }

                if (sendNotificationCheckBox != null) {
                    sendNotificationCheckBox.setText("Send Notification");
                    sendNotificationCheckBox.setTextColor(getResources().getColor(R.color.colorGeneralText));
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();

        if (createEventWindow != null)
        createEventWindow.dismiss();
    }
}
