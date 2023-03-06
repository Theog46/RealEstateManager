package com.openclassrooms.realestatemanager.Database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.openclassrooms.realestatemanager.Model.Photos;

import java.util.List;

@Dao
public interface PhotosDao {

    @Query("SELECT * FROM photos")
    LiveData<List<Photos>> getAllPhotos();

    @Query("SELECT * FROM photos WHERE propertyId = :propertyId ORDER BY id")
    LiveData<List<Photos>> getPhotosByPropertiesId(long propertyId);

    @Insert
    void addPhotos(Photos photos);
}
