package com.gid.gidassistant.view.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gid.gidassistant.R;

public class BestItemAdapter extends RecyclerView.Adapter<BestItemAdapter.BestViewHolder> {

    String titles[];
    String heading[];
    String descriptions[];
    int images[];

    Context context;

    public BestItemAdapter(Context context, String titles[], String heading[] ,String descriptions[], int images[]) {
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

    public class BestViewHolder extends  RecyclerView.ViewHolder {

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

//    class BestItemViewHolder extends RecyclerView.ViewHolder {
//
//        TextView headDescrp;
//        TextView descrp;
//        TextView heading;
//        Image img;
//
//        Context context;
//
//        public BestItemViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//
//            headDescrp = itemView.findViewById(R.id.heading_main);
//            descrp = itemView.findViewById(R.id.description);
//            heading = itemView.findViewById(R.id.heading);
//            img = itemView.findViewById(R.id.image_content);
//        }
//




     /*   Context context;


        BestItemAdapter(Context context, String titles[], String descriptions[], int images[]) {
            super(context, R.layout.best_layout, R.id.fragment_container_view_tag, titles);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.best_item, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.heading_main);
            TextView myDescription = row.findViewById(R.id.description);

            // now set our resources on views
            images.setImageResource(this.images[position]);
            myTitle.setText(titles[position]);
            myDescription.setText(descriptions[position]);


            return row;
        }

    }*/
}
