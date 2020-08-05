package com.gid.gidassistant.view.adapters.best;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.graphics.Rect;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gid.gidassistant.R;

public class BestItemAdapter extends RecyclerView.Adapter<BestItemAdapter.BestViewHolder> {

    private String[] titles;
    private String[] heading;
    private String[] descriptions;
    private int[] images;

    private Context context;

    public BestItemAdapter(Context context, String[] titles, String[] heading ,String[] descriptions, int[] images) {
        this.context = context;
        this.titles = titles;
        this.descriptions = descriptions;
        this.images = images;
        this.heading = heading;
       // super(context, R.layout.best_layout, R.id.fragment_container_view_tag, titles);
    }

    @NonNull
    @Override
    public BestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.best_item, parent,false);
        return new BestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BestViewHolder holder, int position) {
        holder.heading.setText(heading[position]);
        holder.mainHeading.setText(titles[position]);
        holder.description.setText(descriptions[position]);
        holder.image.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    class BestViewHolder extends RecyclerView.ViewHolder {

        TextView heading;
        TextView mainHeading;
        TextView description;
        ImageView image;

        public BestViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.heading);
            mainHeading = itemView.findViewById(R.id.heading_main);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.image_rv);

        }
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration
    {
        private int space;

        public SpacesItemDecoration(int space)
        {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
        {
            //добавить переданное кол-во пикселей отступа снизу
            outRect.bottom = space;
        }
    }
}
