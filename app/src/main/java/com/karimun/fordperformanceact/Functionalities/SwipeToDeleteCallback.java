package com.karimun.fordperformanceact.Functionalities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.karimun.fordperformanceact.Adapters.EventAdapter;
import com.karimun.fordperformanceact.R;


public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private EventAdapter eventAdapter;
    private Drawable iconDelete;
    private final ColorDrawable backgroundDelete;


    public SwipeToDeleteCallback(EventAdapter eAdapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);

        eventAdapter = eAdapter;

        if (eventAdapter.getmContext() != null)
            iconDelete = ContextCompat.getDrawable(eventAdapter.getmContext(), R.drawable.ic_delete_event);

        backgroundDelete = new ColorDrawable(Color.parseColor("#b22222"));
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        final int position = viewHolder.getAdapterPosition();
        eventAdapter.deleteEventOnSwipe(position);
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView
            , @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        View itemView = viewHolder.itemView;

        int iconMargin = (itemView.getHeight() - iconDelete.getIntrinsicHeight()) / 2;
        int iconTop = itemView.getTop() + iconMargin;
        int iconBottom = iconTop + iconDelete.getIntrinsicHeight();

        // When swiping to the right
        if (dX > 0) {
            int iconLeft = itemView.getLeft() + iconMargin;
            int iconRight = iconLeft + iconDelete.getIntrinsicWidth();
            iconDelete.setBounds(iconLeft, iconTop, iconRight, iconBottom);

            backgroundDelete.setBounds(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + ((int) dX), itemView.getBottom());
        }
        // When swiping to the left
        else if (dX < 0) {
            int iconRight = itemView.getRight() - iconMargin;
            int iconLeft = iconRight - iconDelete.getIntrinsicWidth();
            iconDelete.setBounds(iconLeft, iconTop, iconRight, iconBottom);

            backgroundDelete.setBounds(itemView.getRight() + ((int) dX), itemView.getTop(), itemView.getRight(), itemView.getBottom());
        }
        // When unswiped
        else {
            backgroundDelete.setBounds(0, 0, 0, 0);
        }

        backgroundDelete.draw(c);
        iconDelete.draw(c);
    }
}
