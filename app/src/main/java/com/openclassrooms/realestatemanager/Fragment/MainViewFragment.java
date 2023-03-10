package com.openclassrooms.realestatemanager.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.realestatemanager.Injection;
import com.openclassrooms.realestatemanager.MainActivity;
import com.openclassrooms.realestatemanager.Model.Propertie;
import com.openclassrooms.realestatemanager.PropertieAdapter;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.ViewModel.PropertieViewModel;
import com.openclassrooms.realestatemanager.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class MainViewFragment extends Fragment {

    private RecyclerView rv;
    private List<Propertie> properties = new ArrayList<>();
    private PropertieAdapter adapter;
    private PropertieViewModel propertieViewModel;
    private FloatingActionButton addBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main_view, container, false);

        this.configureViewModel();
        this.getPropertieList();

        addBtn = v.findViewById(R.id.add_propertie_btn);

        this.addPropertie();

        return v;
    }

    private void addPropertie() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                Fragment fragment = new AddPropertieFragment();
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, fragment)
                        .addToBackStack("add")
                        .commit();
            }
        });
    }

    private void configureViewModel() {
        ViewModelFactory viewModelFactory = Injection.provideVMF(getContext());
        this.propertieViewModel = new ViewModelProvider(this, viewModelFactory).get(PropertieViewModel.class);
    }

    private void getPropertieList() {
        this.propertieViewModel.getAllProperties().observe(getViewLifecycleOwner(), this::updateProperties);

    }

    private void updateProperties(List<Propertie> properties) {
        this.properties = properties;
        filter();
    }

    private void filter() {
        MainActivity activity = (MainActivity) getActivity();
        Bundle bundle = activity.getBundle();
        if (bundle != null) {
            List<Propertie> filtered = new ArrayList<>();
            List<String> ids = bundle.getStringArrayList("filter");
            for (String string : ids) {
                long id = Long.parseLong(string);
                for (Propertie propertie : properties) {
                    if (propertie.getId() == id) {
                        filtered.add(propertie);
                    }
                }
            }
            init(filtered);
        } else {
            init(properties);
        }
    }

    private void init(List<Propertie> properties) {
        rv = getActivity().findViewById(R.id.properties_rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.addItemDecoration(new DividerItemDecoration(rv.getContext(), DividerItemDecoration.VERTICAL));
        rv.setAdapter(new PropertieAdapter(properties, getContext()));
    }
}
