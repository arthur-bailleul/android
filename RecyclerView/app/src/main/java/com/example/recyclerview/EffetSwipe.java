package com.example.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class EffetSwipe extends ItemTouchHelper.SimpleCallback {
    private Context ctx;
    private MonAdapteur monAdapteur;
    private List<Items> mesItems;

    public EffetSwipe(int dragDirs, int swipeDirs, Context ctx, MonAdapteur monAdapteur, List<Items> items) {
        super(dragDirs, swipeDirs);

        this.ctx = ctx;
        this.monAdapteur = monAdapteur;
        this.mesItems = items;
    }

    public EffetSwipe(Context ctx, MonAdapteur monAdapter, List<Items> items, int swipeDirs) {
        super(0, swipeDirs);

        this.ctx = ctx;
        this.monAdapteur = monAdapter;
        this.mesItems = items;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int pos = viewHolder.getAbsoluteAdapterPosition();
        if (pos == RecyclerView.NO_POSITION) return;

        if (direction == ItemTouchHelper.LEFT) {
            mesItems.remove(pos);
            Toast.makeText(ctx, "Supprimer!", Toast.LENGTH_SHORT).show();
            monAdapteur.notifyItemRemoved(pos);
        }
        if (direction == ItemTouchHelper.RIGHT) {
            mesItems.remove(pos);
            Toast.makeText(ctx, "Archiver", Toast.LENGTH_SHORT).show();
            monAdapteur.notifyItemRemoved(pos);
        }
    }

    @Override
    public void onChildDraw(
            @NonNull Canvas c,
            @NonNull RecyclerView recyclerView,
            @NonNull RecyclerView.ViewHolder viewHolder,
            float dX, float dY,
            int actionState,
            boolean isCurrentlyActive
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        .addSwipeLeftBackgroundColor(ContextCompat.getColor(viewHolder.itemView.getContext(), R.color.red))
        .addSwipeLeftLabel("Supprimer")
        .setSwipeLeftLabelColor(R.color.white)
//        .addSwipeLeftActionIcon(R.drawable.test)
        .addSwipeRightBackgroundColor(ContextCompat.getColor(viewHolder.itemView.getContext(), R.color.vert))
        .addSwipeRightLabel("Archiver")
        .setSwipeRightLabelColor(R.color.white)
//        .addSwipeRightActionIcon(R.drawable.chat7)
        .create()
        .decorate();


    }
}
