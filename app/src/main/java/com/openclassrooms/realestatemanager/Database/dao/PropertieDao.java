package com.openclassrooms.realestatemanager.Database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.openclassrooms.realestatemanager.Model.Propertie;

import java.util.List;

@Dao
public interface PropertieDao {

    @Query("SELECT * FROM Propertie")
    LiveData<List<Propertie>> getAllProperties();

    @Insert
    void addPropertie(Propertie propertie);

    @Update
    void updatePropertie(Propertie propertie);

    @RawQuery
    List<Propertie> getPropertiesFiltered(SupportSQLiteQuery query);
}
