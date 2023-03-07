package com.openclassrooms.realestatemanager.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.openclassrooms.realestatemanager.BuildConfig;
import com.openclassrooms.realestatemanager.EditPropertieActivity;
import com.openclassrooms.realestatemanager.Injection;
import com.openclassrooms.realestatemanager.Model.Photos;
import com.openclassrooms.realestatemanager.Model.Propertie;
import com.openclassrooms.realestatemanager.PropertieDetailMediaAdapter;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.ViewModel.PropertieViewModel;
import com.openclassrooms.realestatemanager.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class PropertieDetailsFragment extends Fragment {

    private List<Photos> photos = new ArrayList<>();
    private RecyclerView rv;
    private PropertieDetailMediaAdapter adapter = new PropertieDetailMediaAdapter(photos);
    private PropertieViewModel propertieViewModel;
    private TextView description;
    private TextView surface;
    private TextView rooms;
    private TextView baths;
    private TextView bedrooms;
    private TextView address;
    private TextView type;
    private TextView entryDate;
    private TextView agentName;
    private ImageView agentPicture;
    private TextView soldDate;
    private TextView points;
    private ImageView map;
    private Button editBtn;
    private Chip school;
    private Chip hospital;
    private Chip supermarket;
    private Chip golf;
    private Chip park;
    private Chip casino;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_propertie_details, container, false);

        rv = v.findViewById(R.id.details_media_rv);
        rv.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false));

        Bundle bundle = this.getArguments();
        if (bundle == null) {
            Bundle bundle1 = new Bundle();
            bundle1.putSerializable("tag", 1);
        } else if (bundle != null) {
            Propertie propertie = (Propertie) bundle.getSerializable("tag");

            description = v.findViewById(R.id.details_description);
            description.setText(propertie.getDescription());
            surface = v.findViewById(R.id.details_surface);
            surface.setText(String.valueOf(propertie.getSurface()));

            rooms = v.findViewById(R.id.details_rooms);
            rooms.setText(String.valueOf(propertie.getRooms()));

            baths = v.findViewById(R.id.details_bath);
            baths.setText(String.valueOf(propertie.getBaths()));

            bedrooms = v.findViewById(R.id.details_bedroom);
            bedrooms.setText(String.valueOf(propertie.getBedrooms()));

            address = v.findViewById(R.id.details_address);
            address.setText(propertie.getAddress());

            type = v.findViewById(R.id.details_property_basics);
            type.setText(propertie.getType() + " located in " + propertie.getState());

            school = v.findViewById(R.id.school);
            school.setChecked(propertie.getSchool());
            school.setCheckable(false);

            hospital = v.findViewById(R.id.hospital);
            hospital.setChecked(propertie.getHospital());
            hospital.setCheckable(false);

            supermarket = v.findViewById(R.id.supermarket);
            supermarket.setChecked(propertie.getSupermarket());
            supermarket.setCheckable(false);

            golf = v.findViewById(R.id.golf);
            golf.setChecked(propertie.getGolf());
            golf.setCheckable(false);

            park = v.findViewById(R.id.park);
            park.setChecked(propertie.getPark());
            park.setCheckable(false);

            casino = v.findViewById(R.id.casino);
            casino.setChecked(propertie.getCasino());
            casino.setCheckable(false);

            entryDate = v.findViewById(R.id.entry_date);
            entryDate.setText(propertie.getEntryDate());

            agentName = v.findViewById(R.id.agent_name);
            agentName.setText(propertie.getAgentName());

            map = v.findViewById(R.id.map_details);

            if (propertie.getLongitude() == null) {
                map.setVisibility(View.GONE);
            } else {
                Glide.with(v.getContext())
                        .load(defineMapUrl())
                        .into(map);
            }

            agentPicture = v.findViewById(R.id.agent_picture);

            Glide.with(v.getContext())
                    .load(propertie.getAgentPicture())
                    .into(agentPicture);

            soldDate = v.findViewById(R.id.sold_date);
            if (propertie.getSoldDate().contains("null")) {
                soldDate.setText("Not sold yet");
            } else {
                soldDate.setText(propertie.getSoldDate());
            }

            editBtn = v.findViewById(R.id.edit_btn);
            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("edit", propertie);

                    Intent intent = new Intent(getActivity(), EditPropertieActivity.class);
                    intent.putExtras(bundle);
                    getActivity().startActivity(intent);


                }
            });
        }



        this.configureViewModel();
        this.getMediaList();

        rv.setAdapter(adapter);

        return v;
    }



    private void configureViewModel() {
        ViewModelFactory viewModelFactory = Injection.provideVMF(getContext());
        this.propertieViewModel = new ViewModelProvider(this, viewModelFactory).get(PropertieViewModel.class);
    }

    private void getMediaList() {
        long propertieId;
        Bundle bundle = this.getArguments();
        if (bundle == null) {
            Bundle bundle1 = new Bundle();
            bundle1.putSerializable("tag", 1);
        } else {
            Propertie propertie = (Propertie) bundle.getSerializable("tag");
            propertieId = propertie.getId();
            this.propertieViewModel.getAllPhotosByPropertiesId(propertieId).observe(getViewLifecycleOwner(), this::updateMedias);

        }
    }



    private void updateMedias(List<Photos> photos) {
        this.photos = photos;
        adapter.updateMedias(photos);
    }

    private String defineMapUrl() {

        Bundle bundle = this.getArguments();
        Propertie propertie = (Propertie) bundle.getSerializable("tag");

        String baseUrl = "https://maps.googleapis.com/maps/api/staticmap";
        String size = "size=400x400";
        String zoom = "zoom=13";
        String params = "center=" + propertie.getLatitude().toString() + "," + propertie.getLongitude().toString();
        String marker = "markers=size:mid%7Ccolor:red%7C" + propertie.getLatitude().toString() + "," + propertie.getLongitude().toString();
        String apiKey = "key=" + BuildConfig.MAPS_API_KEY;

        String url = String.format("%s?%s&%s&%s&%s&%s",
                baseUrl,
                size,
                zoom,
                params,
                marker,
                apiKey);

        Log.d("URL", url);

        return url;
    }


}
