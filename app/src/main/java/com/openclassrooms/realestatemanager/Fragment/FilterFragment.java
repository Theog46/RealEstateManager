package com.openclassrooms.realestatemanager.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputLayout;
import com.openclassrooms.realestatemanager.Injection;
import com.openclassrooms.realestatemanager.MainActivity;
import com.openclassrooms.realestatemanager.Model.Propertie;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.ViewModel.PropertieViewModel;
import com.openclassrooms.realestatemanager.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class FilterFragment extends Fragment {

    PropertieViewModel propertieViewModel;
    List<Object> args = new ArrayList<>();
    private String query =  "SELECT * FROM Propertie";
    private TextInputLayout type;
    private TextInputLayout minPrice;
    private TextInputLayout maxPrice;
    private TextInputLayout minSurface;
    private TextInputLayout maxSurface;
    private TextInputLayout totalRooms;
    private TextInputLayout bathrooms;
    private TextInputLayout bedrooms;
    private Chip school;
    private Chip hospital;
    private Chip supermarket;
    private Chip golf;
    private Chip park;
    private Chip casino;
    Button applyFilter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_filter, container, false);

        ViewModelFactory viewModelFactory = Injection.provideVMF(getContext());
        this.propertieViewModel = new ViewModelProvider(this, viewModelFactory).get(PropertieViewModel.class);

        applyFilter = v.findViewById(R.id.apply);
        applyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFilteredProperties();
            }
        });

        return v;
    }



    private void getFilteredProperties() {
        setQuery();
        Log.d("QUERY", query);
        SimpleSQLiteQuery sqLiteQuery = new SimpleSQLiteQuery(query, args.toArray());
        List<Propertie> properties = propertieViewModel.getFilteredProperties(sqLiteQuery);
        getFilteredPropertiesForMainView(properties);
    }

    private void setQuery() {
        type = getActivity().findViewById(R.id.filter_type);
        String propertieType = type.getEditText().getText().toString();

        minPrice = getActivity().findViewById(R.id.filter_min_price);
        String propertieMinPrice = minPrice.getEditText().getText().toString();

        maxPrice = getActivity().findViewById(R.id.filter_max_price);
        String propertieMaxPrice = maxPrice.getEditText().getText().toString();

        minSurface = getActivity().findViewById(R.id.filter_min_surface);
        String propertieMinSurface = minSurface.getEditText().getText().toString();

        maxSurface = getActivity().findViewById(R.id.filter_max_surface);
        String propertieMaxSurface = maxSurface.getEditText().getText().toString();

        totalRooms = getActivity().findViewById(R.id.filter_min_rooms);
        String propertieMinRooms = totalRooms.getEditText().getText().toString();

        bathrooms = getActivity().findViewById(R.id.filter_min_bathrooms);
        String propertieMinBaths = bathrooms.getEditText().getText().toString();

        bedrooms = getActivity().findViewById(R.id.filter_min_bedrooms);
        String propertieMinBeds = bedrooms.getEditText().getText().toString();

        school = getActivity().findViewById(R.id.school);
        Boolean isSchool = school.isChecked();

        hospital = getActivity().findViewById(R.id.hospital);
        Boolean isHospital = hospital.isChecked();

        supermarket = getActivity().findViewById(R.id.supermarket);
        Boolean isSupermarket = supermarket.isChecked();

        golf = getActivity().findViewById(R.id.golf);
        Boolean isGolf = golf.isChecked();

        park = getActivity().findViewById(R.id.park);
        Boolean isPark = park.isChecked();

        casino = getActivity().findViewById(R.id.casino);
        Boolean isCasino = casino.isChecked();


        boolean b = false;
        if (propertieType.length() > 2) {
            if (b) {
                query += " AND type = ?";
            } else {
                query += " WHERE type = ?";
                b = true;
            }
            args.add(propertieType);
        }
        if (propertieMinPrice.length() != 0) {
            if (b) {
                query += " AND price >= :";
            } else {
                query += " WHERE price >= :";
                b = true;
            }
            query += propertieMinPrice;
            args.add(propertieMinPrice);
        }
        if (propertieMaxPrice.length() > 2) {
            if (b) {
                query += " AND price <= :";
            } else {
                query += " WHERE price <= :";
                b = true;
            }
            query += propertieMaxPrice;
            args.add(propertieMaxPrice);
        }
        if (propertieMinSurface.length() > 1) {
            if (b) {
                query += " AND surface >= :";
            } else {
                query += " WHERE surface >= :";
                b = true;
            }
            query += propertieMinSurface;
            args.add(propertieMinPrice);
        }
        if (propertieMaxSurface.length() > 1) {
            if (b) {
                query += " AND surface <= :";
            } else {
                query += " WHERE surface <= :";
                b = true;
            }
            query += propertieMaxSurface;
            args.add(propertieMaxSurface);
        }
        if (propertieMinRooms.length() >= 1) {
            if (b) {
                query += " AND rooms >= ?";
            } else {
                query += " WHERE rooms >= ?";
                b = true;
            }
            args.add(propertieMinRooms);
        }
        if (propertieMinBaths.length() >= 1) {
            if (b) {
                query += " AND baths >= ?";
            } else {
                query += " WHERE baths >= ?";
                b = true;
            }
            args.add(propertieMinBaths);
        }
        if (propertieMinBeds.length() >= 1) {
            if (b) {
                query += " AND bedrooms >= ?";
            } else {
                query += " WHERE bedrooms >= ?";
                b = true;
            }
            args.add(propertieMinBeds);
        }
        if (isSchool) {
            if (b) {
                query += " AND school = ?";
            } else {
                query += " WHERE school = ?";
                b = true;
            }
            args.add(true);

        }
        if (isHospital) {
            if (b) {
                query += " AND hospital = ?";
            } else {
                query += " WHERE hospital = ?";
                b = true;
            }
            args.add(true);

        }
        if (isSupermarket) {
            if (b) {
                query += " AND supermarket = ?";
            } else {
                query += " WHERE supermarket = ?";
                b = true;
            }
            args.add(true);
        }
        if (isGolf) {
            if (b) {
                query += " AND golf = ?";
            } else {
                query += " WHERE golf = ?";
                b = true;
            }
            args.add(true);
        }
        if (isPark) {
            if (b) {
                query += " AND park = ?";
            } else {
                query += " WHERE park = ?";
                b = true;
            }
            args.add(true);
        }
        if (isCasino) {
            if (b) {
                query += " AND casino = ?";
            } else {
                query += " WHERE casino = ?";
                b = true;
            }
            args.add(true);
        }
    }

    private void getFilteredPropertiesForMainView(List<Propertie> properties) {
        List<String> propertiesId = new ArrayList<>();
        for (Propertie propertie : properties) {
            propertiesId.add(String.valueOf(propertie.getId()));
        }
        openMainActivity(propertiesId);
    }

    private void openMainActivity(List<String> propertiesId) {
        final Bundle bundle = new Bundle();
        bundle.putStringArrayList("filter", (ArrayList<String>) propertiesId);
        Intent i = new Intent(requireActivity(), MainActivity.class);
        i.putExtras(bundle);
        startActivity(i);
    }


}
