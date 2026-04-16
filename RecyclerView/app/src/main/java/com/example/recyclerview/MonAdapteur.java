package com.example.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class MonAdapteur extends RecyclerView.Adapter<MonAdapteur.MonViewHolder> {
    List<Items> mesItems;
    Context ctx;

    public MonAdapteur(List<Items> mesItems, Context ctx) {
        this.mesItems = mesItems;
        this.ctx = ctx;
    }


    public class MonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView_img;
        private TextView textView_titre, textView_desc;

        public MonViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView_img = itemView.findViewById(R.id.imageView);
            textView_titre = itemView.findViewById(R.id.textView_titre);
            textView_desc = itemView.findViewById(R.id.textView_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getLayoutPosition();

            String titreClick = mesItems.get(pos).getTextView_titre();
            String descriptionClick = mesItems.get(pos).getTextView_description();
            int imgClick = mesItems.get(pos).getImageView_img();

            Log.d("Click", "click");
            Log.d("Click", "titre"+titreClick);
            Log.d("Click", "description"+descriptionClick);
            Log.d("Click", "img"+imgClick);
//            Context ctx = view.getContext();
//            Toast.makeText(view.getContext(), "", Toast.LENGTH_SHORT).show();
            Toast.makeText(ctx, titreClick, Toast.LENGTH_SHORT).show();
            Toast.makeText(ctx, descriptionClick, Toast.LENGTH_SHORT).show();
            Toast.makeText(ctx, imgClick, Toast.LENGTH_SHORT).show();
        }
    }


    @NonNull
    @Override
    public MonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.affichage_items, parent, false);
        return new MonViewHolder(view);
        // exec 1 seul fois
        // c'est ici quon charge la vue .xml de notre patron
    }

    @Override
    public void onBindViewHolder(@NonNull MonViewHolder holder, int position) {
        holder.imageView_img.setImageResource(mesItems.get(position).getImageView_img());
        holder.textView_titre.setText(mesItems.get(position).getTextView_titre());
        holder.textView_desc.setText(mesItems.get(position).getTextView_description());
    }

    @Override
    public int getItemCount() {
        return mesItems.size();
    }
}
