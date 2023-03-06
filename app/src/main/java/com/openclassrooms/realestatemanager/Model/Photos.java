package com.openclassrooms.realestatemanager.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "photos")
public class Photos {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long propertyId;
    private String url;
    private String legend;

    public Photos() {}

    public Photos(long id, long propertyId, String url, String legend) {
        this.id = id;
        this.propertyId = propertyId;
        this.url = url;
        this.legend = legend;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public long getPropertyId() { return propertyId; }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    public String getUrl() { return url; }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLegend() { return legend; }

    public void setLegend(String legend) {
        this.legend = legend;
    }
}
