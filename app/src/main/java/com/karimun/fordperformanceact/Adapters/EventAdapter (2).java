package com.karimun.fordperformanceact.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karimun.fordperformanceact.MainActivity;
import com.karimun.fordperformanceact.Models.OurEvent;
import com.karimun.fordperformanceact.R;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private Context mContext;
    private List<OurEvent> events;
    private boolean isEventCalendar2;

    private OurEvent recentlyArchivedEvent;

    public EventAdapter(Context mContext, List<OurEvent> events, boolean isEventCalendar2) {
        this.mContext = mContext;
        this.events = events;
        this.isEventCalendar2 = isEventCalendar2;
    }

    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_event, viewGroup, false);
        return new EventAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        OurEvent ourEvent = events.get(i);

        viewHolder.eventTitle.setText(ourEvent.getTitle());
        viewHolder.timeStamp.setText(ourEvent.getTimeStart() + " - " + ourEvent.getTimeEnd());
        viewHolder.locationStamp.setText(ourEvent.getLocation());

        if (isEventCalendar2) {
            viewHolder.dateWrapper.setVisibility(View.VISIBLE);
            viewHolder.eventListBottomBorder.setVisibility(View.VISIBLE);
            viewHolder.eventListContainer.setBackgroundResource(0);
            viewHolder.eventListContainer.setBackgroundColor(Color.parseColor("#245672"));
            viewHolder.eventListContainer.setPadding(0, 0, 0, 0);

            LinearLayout.LayoutParams paramsForEventListContainer = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            paramsForEventListContainer.setMargins(0, 0, 0, 0);
            viewHolder.eventListContainer.setLayoutParams(paramsForEventListContainer);

            RelativeLayout.LayoutParams paramsForEventDetailsWrapper = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            paramsForEventDetailsWrapper.setMargins(100, 0, 100, 0);
            viewHolder.eventDetailsWrapper.setLayoutParams(paramsForEventDetailsWrapper);

            viewHolder.eventTitle.setTextColor(Color.parseColor("#d9d9d9"));
            viewHolder.timeStamp.setTextColor(Color.parseColor("#d9d9d9"));
            viewHolder.locationStamp.setTextColor(Color.parseColor("#d9d9d9"));
            viewHolder.eventLeftBar.setVisibility(View.VISIBLE);
        }

        viewHolder.dateStamp.setText(ourEvent.getDayOfWeekStart() + ", " + ourEvent.getDateStart());


        if (ourEvent.getTimeStart() != null && ourEvent.getDateEnd() != null && ourEvent.getTimeEnd() != null
                && ourEvent.getTimeStart().equals("") && ourEvent.getDateEnd().equals("") && ourEvent.getTimeEnd().equals(""))
            viewHolder.timeStamp.setText("All day");


        // Show how many days left before event occurs
        try {

            // Days
            String labeledDate = viewHolder.dateStamp.getText().toString().substring(5);

            Calendar currentDay = Calendar.getInstance();
            final Calendar startDay = Calendar.getInstance();
            final Calendar endDay = Calendar.getInstance();

            SimpleDateFormat ourDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
            startDay.setTime(ourDateFormat.parse(labeledDate));

            if (!ourEvent.getDateEnd().equals(""))
                endDay.setTime(ourDateFormat.parse(ourEvent.getDateEnd()));

            long finalDayCurrent = TimeUnit.MILLISECONDS.toDays(currentDay.getTimeInMillis());
            long finalDayStart = TimeUnit.MILLISECONDS.toDays(startDay.getTimeInMillis()) + 1;
            long daysLeftBeforeEventStarts = finalDayStart - finalDayCurrent;

            long finalDayEnd = TimeUnit.MILLISECONDS.toDays(endDay.getTimeInMillis()) + 1;
            long daysLeftBeforeEventEnds = finalDayEnd - finalDayCurrent;

            // Hours and minutes
            Calendar currentTime = Calendar.getInstance();
            Calendar targetTimeStart = Calendar.getInstance();
            Calendar targetTimeEnd = Calendar.getInstance();

            SimpleDateFormat ourTimeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());

            // If not all-day
            if (!ourEvent.getTimeStart().equals("") && !ourEvent.getTimeEnd().equals("")) {

                String labeledTimeStart = viewHolder.timeStamp.getText().toString().substring(0, 8);
                String labeledTimeEnd = viewHolder.timeStamp.getText().toString().substring(11);

                targetTimeStart.setTime(ourTimeFormat.parse(labeledTimeStart));
                targetTimeEnd.setTime(ourTimeFormat.parse(labeledTimeEnd));
            }

            int finalHourCurrent = currentTime.get(Calendar.HOUR_OF_DAY);
            int finalHourStart = targetTimeStart.get(Calendar.HOUR_OF_DAY);
            int finalHourEnd = targetTimeEnd.get(Calendar.HOUR_OF_DAY);

            int finalMinuteCurrent = currentTime.get(Calendar.MINUTE);
            int finalMinuteStart = targetTimeStart.get(Calendar.MINUTE);
            int finalMinuteEnd = targetTimeEnd.get(Calendar.MINUTE);

            // Determine number of days left
            if (daysLeftBeforeEventStarts > 0) {

                if (daysLeftBeforeEventStarts == 1) {
                    viewHolder.dayCounter.setText("Tomorrow");
                } else {
                    viewHolder.dayCounter.setText(daysLeftBeforeEventStarts + " days");
                }
            } else if (daysLeftBeforeEventStarts == 0) {

                // Determine if event is on now or has expired
                if (!viewHolder.timeStamp.getText().toString().equals("All day")) {

                    if (finalDayStart == finalDayEnd) {

                        if (finalHourCurrent > finalHourStart)
                            viewHolder.dayCounter.setText("Now");

                        if (finalHourCurrent < finalHourStart)
                            viewHolder.dayCounter.setText("Today");

                        if (finalHourCurrent == finalHourStart)
                            if (finalMinuteCurrent < finalMinuteStart) {
                                viewHolder.dayCounter.setText("Today");
                            } else {
                                viewHolder.dayCounter.setText("Now");
                            }

                        if (finalHourCurrent > finalHourEnd)
                            viewHolder.dayCounter.setText("Expired");

                        if (finalHourCurrent == finalHourEnd)
                            if (finalMinuteCurrent > finalMinuteEnd)
                                viewHolder.dayCounter.setText("Expired");
                    } else {
                        if (finalHourCurrent > finalHourStart)
                            viewHolder.dayCounter.setText("Now");

                        if (finalHourCurrent < finalHourStart)
                            viewHolder.dayCounter.setText("Today");

                        if (finalHourCurrent == finalHourStart)
                            if (finalMinuteCurrent < finalMinuteStart) {
                                viewHolder.dayCounter.setText("Today");
                            } else {
                                viewHolder.dayCounter.setText("Now");
                            }
                    }
                } else {
                    viewHolder.dayCounter.setText("Now");
                }
            } else {

                if (viewHolder.timeStamp.getText().toString().equals("All day")) {
                    viewHolder.dayCounter.setText("Expired");
                } else {

                    if (daysLeftBeforeEventEnds > 0) {
                        viewHolder.dayCounter.setText("Now");
                    } else if (daysLeftBeforeEventEnds == 0) {
                        if (finalHourCurrent > finalHourEnd) {
                            viewHolder.dayCounter.setText("Expired");
                        } else if (finalHourCurrent == finalHourEnd) {
                            if (finalMinuteCurrent > finalMinuteEnd) {
                                viewHolder.dayCounter.setText("Expired");
                            } else {
                                viewHolder.dayCounter.setText("Now");
                            }
                        } else {
                            viewHolder.dayCounter.setText("Now");
                        }
                    } else {
                        viewHolder.dayCounter.setText("Expired");
                    }
                }
            }
        } catch (ParseException e) {
            Log.d("PARSE EXCEPTION", e.getMessage());
        }


        if (viewHolder.dayCounter.getText() != null && ourEvent.getEventId() != null) {
            final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Event").child(ourEvent.getEventId());
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("dayCounter", viewHolder.dayCounter.getText().toString());
            reference.updateChildren(hashMap);
        }

        // Method used to delete events that have expired
        deleteEventWhenExpired();
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView eventTitle;
        TextView timeStamp;
        TextView locationStamp;
        RelativeLayout dateWrapper;
        TextView dateStamp;
        TextView dayCounter;
        ImageView eventLeftBar;
        View eventListBottomBorder;
        LinearLayout eventListContainer;
        LinearLayout eventDetailsWrapper;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            eventTitle = itemView.findViewById(R.id.event_title);
            timeStamp = itemView.findViewById(R.id.time_stamp);
            locationStamp = itemView.findViewById(R.id.location_stamp);
            dateWrapper = itemView.findViewById(R.id.date_wrapper);
            dateStamp = itemView.findViewById(R.id.date_stamp);
            dayCounter = itemView.findViewById(R.id.day_counter);
            eventLeftBar = itemView.findViewById(R.id.event_left_bar);
            eventListBottomBorder = itemView.findViewById(R.id.event_list_bottom_border);
            eventListContainer = itemView.findViewById(R.id.event_list_container);
            eventDetailsWrapper = itemView.findViewById(R.id.event_details_wrapper);
        }
    }

    private void deleteEventWhenExpired() {
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Event");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    OurEvent ourEvent = snapshot.getValue(OurEvent.class);

                    if (ourEvent != null && ourEvent.getDayCounter() != null && ourEvent.getDayCounter().equals("Expired")) {
                        snapshot.getRef().removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void deleteEventOnSwipe(int position) {

        recentlyArchivedEvent = events.get(position);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Event").child(recentlyArchivedEvent.getEventId());
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("isEventArchived", true);
        reference.updateChildren(hashMap);
        recentlyArchivedEvent.setEventArchived(true);

        showUndoSnackbar();
    }

    private void showUndoSnackbar() {

        View view = MainActivity.fragmentContainer;
        Snackbar snackbar = Snackbar.make(view, "Event will be deleted shortly.", Snackbar.LENGTH_LONG);
        snackbar.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Event").child(recentlyArchivedEvent.getEventId());
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("isEventArchived", false);
                reference.updateChildren(hashMap);
                recentlyArchivedEvent.setEventArchived(false);
            }
        });


        snackbar.addCallback(new Snackbar.Callback() {

            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);

                if (recentlyArchivedEvent.isEventArchived()) {
                    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Event").child(recentlyArchivedEvent.getEventId());
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            dataSnapshot.getRef().removeValue();
                            Toast.makeText(mContext, "Event has been removed.", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });


        snackbar.show();
    }

    public Context getmContext() {
        return mContext;
    }

    public List<OurEvent> getEvents() {
        return events;
    }
}
