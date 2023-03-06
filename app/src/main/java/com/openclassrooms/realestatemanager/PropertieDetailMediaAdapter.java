package com.openclassrooms.realestatemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.openclassrooms.realestatemanager.Model.Photos;
import java.util.List;

public class PropertieDetailMediaAdapter extends RecyclerView.Adapter<PropertieDetailMediaAdapter.PropertieDetailMediaViewHolder>{

    private List<Photos> photos;

    public PropertieDetailMediaAdapter(@NonNull final List<Photos> photos) {
        this.photos = photos;
    }

    public void updateMedias(@NonNull final List<Photos> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PropertieDetailMediaAdapter.PropertieDetailMediaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_propertie_details_items, viewGroup, false);
        return new PropertieDetailMediaAdapter.PropertieDetailMediaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertieDetailMediaAdapter.PropertieDetailMediaViewHolder mediaViewHolder, int position) {
        mediaViewHolder.bind(photos.get(position));


    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class PropertieDetailMediaViewHolder extends RecyclerView.ViewHolder {

        private final TextView details;
        private final ImageView picture;

        private Context context;

        PropertieDetailMediaViewHolder(@NonNull View v) {
            super(v);

            details = itemView.findViewById(R.id.details_media_text);
            picture = itemView.findViewById(R.id.details_media_img);

            this.displayImageFullScreen();
        }

        private void displayImageFullScreen() {
            picture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        void bind(Photos photos) {
            details.setText(photos.getLegend());

            Glide.with(this.itemView.getContext())
                    .load(photos.getUrl())
                    .into(picture);
        }

    }
}
