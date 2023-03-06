package com.openclassrooms.realestatemanager.Repository;

import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.openclassrooms.realestatemanager.Database.dao.AgentsDao;
import com.openclassrooms.realestatemanager.Database.dao.PhotosDao;
import com.openclassrooms.realestatemanager.Database.dao.PropertieDao;
import com.openclassrooms.realestatemanager.Model.Agents;
import com.openclassrooms.realestatemanager.Model.Photos;
import com.openclassrooms.realestatemanager.Model.Propertie;

import java.util.List;

public class PropertieRepository {

    private PropertieDao propertieDao;
    private PhotosDao photosDao;
    private AgentsDao agentsDao;

    public PropertieRepository(PropertieDao propertieDao, PhotosDao photosDao, AgentsDao agentsDao) {
        this.propertieDao = propertieDao;
        this.photosDao = photosDao;
        this.agentsDao = agentsDao;
    }

    public LiveData<List<Propertie>> getAllProperties() {
        return this.propertieDao.getAllProperties();
    }

    public void addPropertie(Propertie propertie) {
        propertieDao.addPropertie(propertie);
    }

    public void updatePropertie(Propertie propertie) { propertieDao.updatePropertie(propertie); }

    public void addPhotos(Photos photos) {
        photosDao.addPhotos(photos);
    }

    public LiveData<List<Photos>> getAllPhotos() {
        return this.photosDao.getAllPhotos();
    }

    public LiveData<List<Photos>> getAllPhotosByPropertiesId(long propertyId) { return this.photosDao.getPhotosByPropertiesId(propertyId); }

    public LiveData<List<Agents>> getAllAgents() { return this.agentsDao.getAllAgents(); }

    public List<Propertie> getFilteredProperties(SupportSQLiteQuery query) {
        return propertieDao.getPropertiesFiltered(query);
    }

}
