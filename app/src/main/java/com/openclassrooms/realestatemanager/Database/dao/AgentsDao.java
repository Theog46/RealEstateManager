package com.openclassrooms.realestatemanager.Database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.openclassrooms.realestatemanager.Model.Agents;

import java.util.List;

@Dao
public interface AgentsDao {

    @Query("SELECT * FROM agents")
    LiveData<List<Agents>> getAllAgents();

}
