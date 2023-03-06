package com.openclassrooms.realestatemanager.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.openclassrooms.realestatemanager.Model.Agents;
import com.openclassrooms.realestatemanager.Model.Photos;
import com.openclassrooms.realestatemanager.Model.Propertie;
import com.openclassrooms.realestatemanager.Repository.PropertieRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class PropertieViewModel extends ViewModel {

    private final PropertieRepository propertieRepository;
    private final Executor executor;


    public LiveData<List<Propertie>> getAllProperties() {
        return propertieRepository.getAllProperties();
    }

    public void addPropertie(Propertie propertie) {
        executor.execute(() -> propertieRepository.addPropertie(propertie));
    }

    public void updatePropertie(Propertie propertie) {
        executor.execute(() -> propertieRepository.updatePropertie(propertie));
    }



    public void addPhotos(Photos photos) {
        executor.execute(() -> propertieRepository.addPhotos(photos));
    }

    public LiveData<List<Photos>> getAllPhotos() {
        return propertieRepository.getAllPhotos();
    }

    public LiveData<List<Photos>> getAllPhotosByPropertiesId(long propertieId) { return propertieRepository.getAllPhotosByPropertiesId(propertieId); }

    public LiveData<List<Agents>> getAllAgents() { return propertieRepository.getAllAgents(); }

    public List<Propertie> getFilteredProperties(SupportSQLiteQuery query) {
        executor.execute(() -> propertieRepository.getFilteredProperties(query));
        return propertieRepository.getFilteredProperties(query);
    }


    public PropertieViewModel(PropertieRepository propertieRepository, Executor executor) {
        this.propertieRepository  = propertieRepository;
        this.executor = executor;
    }
}
