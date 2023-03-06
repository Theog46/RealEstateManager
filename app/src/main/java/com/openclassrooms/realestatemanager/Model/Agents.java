package com.openclassrooms.realestatemanager.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "agents")
public class Agents {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String photo;

    public Agents() {}

    public Agents(long id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPhoto() { return photo; }

    public void setPhoto(String photo) { this.photo = photo; }

    @Override
    public String toString() {
        return name;
    }
}
