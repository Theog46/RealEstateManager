package com.openclassrooms.realestatemanager.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.openclassrooms.realestatemanager.Injection;
import com.openclassrooms.realestatemanager.Model.Propertie;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.ViewModel.PropertieViewModel;
import com.openclassrooms.realestatemanager.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class GoogleMapFragment extends Fragment implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap map;
    private Location userLoc;
    FusedLocationProviderClient fusedLocationProviderClient;
    PropertieViewModel propertieViewModel;
    List<Propertie> properties = new ArrayList<>();
    public LatLng propertieLatLng;

    @SuppressLint("MissingPermission")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        ViewModelFactory viewModelFactory = Injection.provideVMF(getContext());
        this.propertieViewModel = new ViewModelProvider(this, viewModelFactory).get(PropertieViewModel.class);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

        return v;
    }



    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            enableUserLocation();
            zoomToUserLocation();
            addPropertiesMarkers();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
                refreshMap();
            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        refreshMap();
    }

    @SuppressLint("MissingPermission")
    private void enableUserLocation() {
        map.setMyLocationEnabled(true);
    }

    private void zoomToUserLocation() {
        @SuppressLint("MissingPermission")
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
            }
        });
    }

    private void addPropertiesMarkers() {
        propertieViewModel.getAllProperties().observe(getViewLifecycleOwner(), properties1 -> {
            addMarkers(properties1);
        });

    }

    private void addMarkers(List<Propertie> properties) {
        map.clear();
        for (Propertie items : properties) {
            Marker marker;
            marker = showMarkers(items);
            propertieLatLng = new LatLng(items.getLatitude(), items.getLongitude());
        }
    }

    private Marker showMarkers(Propertie propertie) {

        if (propertie.getSoldDate().contains("null")) {
            return map.addMarker(new MarkerOptions()
                    .position(new LatLng(propertie.getLatitude(),
                            propertie.getLongitude()))
                    .snippet(propertie.getPrice().toString() + "$")
                    .title(propertie.getType()));
        } if (propertie.getSoldDate() != "null") {
            return map.addMarker(new MarkerOptions()
                    .position(new LatLng(propertie.getLatitude(),
                            propertie.getLongitude()))
                    .snippet(propertie.getPrice().toString() + "$")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title(propertie.getType()));
        }
        return null;
    }

    private void refreshMap() {
        map.clear();
        enableUserLocation();
        zoomToUserLocation();
        addPropertiesMarkers();
    }

}
