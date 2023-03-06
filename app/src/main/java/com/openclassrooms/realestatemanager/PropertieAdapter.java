package com.openclassrooms.realestatemanager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.openclassrooms.realestatemanager.Fragment.PropertieDetailsFragment;
import com.openclassrooms.realestatemanager.Model.Propertie;

import java.text.NumberFormat;
import java.util.List;

public class PropertieAdapter extends RecyclerView.Adapter<PropertieAdapter.PropertieViewHolder> {

    private List<Propertie> properties;
    private Context context;

    public PropertieAdapter(@NonNull final List<Propertie> properties, Context context) {
        this.properties = properties;
        this.context = context;
    }

    public void updateProperties(@NonNull final List<Propertie> properties) {
        this.properties = properties;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PropertieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_main_items, viewGroup, false);
        return new PropertieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertieViewHolder propertieViewHolder, int position) {
        propertieViewHolder.bind(properties.get(position));

        propertieViewHolder.itemView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("tag", properties.get(position));

            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Fragment fragment = new PropertieDetailsFragment();
            fragment.setArguments(bundle);
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, fragment, "detail")
                    .addToBackStack("detail")
                    .commit();

        });
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }


    class PropertieViewHolder extends RecyclerView.ViewHolder {

        private final TextView type;
        private final TextView city;
        private final TextView price;
        private final ImageView picture;
        private final ImageView sold;

        private Context context;

        PropertieViewHolder(@NonNull View v) {
            super(v);

            type = itemView.findViewById(R.id.properties_type_rv);
            city = itemView.findViewById(R.id.properties_city_rv);
            price = itemView.findViewById(R.id.properties_price_rv);
            picture = itemView.findViewById(R.id.properties_main_image_rv);
            sold = itemView.findViewById(R.id.is_sold);
        }

        void bind(Propertie propertie) {
            type.setText(propertie.getType());
            city.setText(propertie.getState());
            NumberFormat nf = NumberFormat.getInstance();
            String curPrice = nf.format(propertie.getPrice());
            price.setText("$" + curPrice);


            Glide.with(this.itemView.getContext())
                    .load(propertie.getMainImg())
                    .into(picture);



            if (propertie.getSoldDate().contains("null")) {
                sold.setVisibility(View.INVISIBLE);
            } else {
                sold.setVisibility(View.VISIBLE);
            }
        }

    }
}
