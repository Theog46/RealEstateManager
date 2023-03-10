package com.openclassrooms.realestatemanager.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputLayout;
import com.openclassrooms.realestatemanager.AgentAdapter;
import com.openclassrooms.realestatemanager.Injection;
import com.openclassrooms.realestatemanager.Model.Agents;
import com.openclassrooms.realestatemanager.Model.Photos;
import com.openclassrooms.realestatemanager.Model.Propertie;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.Utils;
import com.openclassrooms.realestatemanager.ViewModel.PropertieViewModel;
import com.openclassrooms.realestatemanager.ViewModelFactory;

import java.util.ArrayList;

public class AddPropertieFragment extends Fragment {

    PropertieViewModel propertieViewModel;
    private TextInputLayout type;
    private TextInputLayout price;
    private TextInputLayout state;
    private TextInputLayout description;
    private TextInputLayout address;
    private TextInputLayout totalRooms;
    private TextInputLayout bathrooms;
    private TextInputLayout bedrooms;
    private TextInputLayout surface;
    private TextInputLayout photosLegend;
    private TextInputLayout agent;
    private Chip school;
    private Chip hospital;
    private Chip supermarket;
    private Chip golf;
    private Chip park;
    private Chip casino;
    private ImageView agentImg;
    private Button imageBtn;
    private Button btn;
    private Uri imageUri = null;
    String agenturl;
    AutoCompleteTextView autoCompleteTextView;
    AgentAdapter adapter;
    private ArrayList<Agents> agentsList = new ArrayList<>();
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location lastKnownLocation;
    double latitude;
    double longitude;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_propertie, container, false);

        configureViewModel();

        this.propertieViewModel.getAllAgents().observe(getViewLifecycleOwner(), value -> {
            agentsList.clear();
            agentsList.addAll(value);
        });

        AgentAdapter adapter = new AgentAdapter(getActivity(), agentsList);

        autoCompleteTextView = v.findViewById(R.id.edittextState);

        autoCompleteTextView.setAdapter(adapter);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        } else {
            getLastLocation();
        }

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                agenturl = agentsList.get(i).getPhoto();
            }
        });

        btn = v.findViewById(R.id.add_propertie_final_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPropertie();
                Toast.makeText(getActivity(), "You successfully created a new Propertie.", Toast.LENGTH_LONG).show();
                getActivity().onBackPressed();


            }
        });

        imageBtn = v.findViewById(R.id.add_propertie_image_btn);
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
            }
        });

        return v;
    }


    private void configureViewModel() {
        ViewModelFactory viewModelFactory = Injection.provideVMF(getContext());
        this.propertieViewModel = new ViewModelProvider(this, viewModelFactory).get(PropertieViewModel.class);

    }


    private void createPropertie() {
        type = getActivity().findViewById(R.id.add_propertie_type);
        String propertieType = type.getEditText().getText().toString();

        price = getActivity().findViewById(R.id.add_propertie_price);
        Integer propertiePrice = Integer.parseInt(price.getEditText().getText().toString());

        state = getActivity().findViewById(R.id.add_propertie_state);
        String propertieState = state.getEditText().getText().toString();

        description = getActivity().findViewById(R.id.add_propertie_description);
        String propertieDescription = description.getEditText().getText().toString();

        address = getActivity().findViewById(R.id.add_propertie_address);
        String propertieAddress = address.getEditText().getText().toString();

        totalRooms = getActivity().findViewById(R.id.add_propertie_rooms);
        Integer propertieTotalRooms = Integer.parseInt(totalRooms.getEditText().getText().toString());

        bathrooms = getActivity().findViewById(R.id.add_propertie_baths);
        Integer propertieBaths = Integer.parseInt(bathrooms.getEditText().getText().toString());

        bedrooms = getActivity().findViewById(R.id.add_propertie_bedrooms);
        Integer propertieBedrooms = Integer.parseInt(bedrooms.getEditText().getText().toString());

        surface = getActivity().findViewById(R.id.add_propertie_surface);
        Integer propertieSurface = Integer.parseInt(surface.getEditText().getText().toString());

        agent = getActivity().findViewById(R.id.add_propertie_agent);
        String agentName = agent.getEditText().getText().toString();

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

        long id = (long) (Math.random() * 50000);

        Propertie propertie = new Propertie();
        propertie.setId(id);
        propertie.setType(propertieType);
        propertie.setPrice(propertiePrice);
        propertie.setState(propertieState);
        propertie.setAgentName(agentName);
        propertie.setAgentPicture(agenturl);
        propertie.setDescription(propertieDescription);
        propertie.setAddress(propertieAddress);
        propertie.setRooms(propertieTotalRooms);
        propertie.setBaths(propertieBaths);
        propertie.setBedrooms(propertieBedrooms);
        propertie.setSurface(propertieSurface);
        propertie.setEntryDate(Utils.getTodayDate());
        propertie.setSoldDate("null");
        propertie.setMainImg(imageUri.toString());
        propertie.setHospital(isHospital);
        propertie.setSchool(isSchool);
        propertie.setGolf(isGolf);
        propertie.setSupermarket(isSupermarket);
        propertie.setPark(isPark);
        propertie.setCasino(isCasino);
        propertie.setLatitude(latitude);
        propertie.setLongitude(longitude);

        propertieViewModel.addPropertie(propertie);

        photosLegend = getActivity().findViewById(R.id.photo_detail);
        String description = photosLegend.getEditText().getText().toString();

        Photos photos = new Photos();
        photos.setPropertyId(id);
        photos.setUrl(imageUri.toString());
        photos.setLegend(description);


        propertieViewModel.addPhotos(photos);

    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        fusedLocationProviderClient.getLastLocation()
                .addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            lastKnownLocation = task.getResult();
                            latitude = lastKnownLocation.getLatitude();
                            longitude = lastKnownLocation.getLongitude();

                        } else {
                            Toast.makeText(getContext(), "Couldn't get your position, please retry when you are connected to a network.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    private void pickImage() {
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        i.setType("image/*");
        galleryActivityResultLauncher.launch(i);
    }

    private ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        imageUri = data.getData();
                        ImageView image;
                        image = getActivity().findViewById(R.id.add_propertie_image);
                        image.setImageURI(imageUri);
                        image.setVisibility(View.VISIBLE);
                    } else {

                    }
                }
            }
    );

}
