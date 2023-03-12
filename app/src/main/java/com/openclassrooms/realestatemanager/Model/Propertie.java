package com.openclassrooms.realestatemanager.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "propertie")
public class Propertie implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String mainImg;
    private String type;
    private Integer price;
    private Integer surface;
    private Integer rooms;
    private Integer baths;
    private Integer bedrooms;
    private String description;
    private String address;
    private String state;
    private String entryDate;
    private String soldDate;
    private long agentId;
    private String agentName;
    private String agentPicture;
    private Boolean school;
    private Boolean hospital;
    private Boolean supermarket;
    private Boolean golf;
    private Boolean park;
    private Boolean casino;
    private Double latitude;
    private Double longitude;

    public Propertie() {}

    public Propertie(long id, String mainImg, String type, Integer price, Integer surface, Integer rooms, Integer baths, Integer bedrooms, String description, String address, String state, String entryDate, String soldDate, long agentId, String agentName, String agentPicture, Boolean school, Boolean hospital, Boolean supermarket, Boolean golf, Boolean park, Boolean casino, Double latitude, Double longitude) {
        this.id = id;
        this.mainImg = mainImg;
        this.type = type;
        this.price = price;
        this.surface = surface;
        this.rooms = rooms;
        this.baths = baths;
        this.bedrooms = bedrooms;
        this.description = description;
        this.address = address;
        this.state = state;
        this.entryDate = entryDate;
        this.soldDate = soldDate;
        this.agentId = agentId;
        this.agentName = agentName;
        this.agentPicture = agentPicture;
        this.school = school;
        this.hospital = hospital;
        this.supermarket = supermarket;
        this.golf = golf;
        this.park = park;
        this.casino = casino;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId()  { return id; }

    public void setId(long id) { this.id = id; }

    public String getMainImg() { return mainImg; }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public Integer getPrice() { return price; }

    public void setPrice(Integer price) { this.price = price; }

    public Integer getSurface() { return surface; }

    public void setSurface(Integer surface) { this.surface = surface; }

    public Integer getRooms() { return rooms; }

    public void setRooms(Integer rooms) { this.rooms = rooms; }

    public Integer getBaths() { return baths; }

    public void setBaths(Integer baths) { this.baths = baths; }

    public Integer getBedrooms() { return bedrooms; }

    public void setBedrooms(Integer bedrooms) { this.bedrooms = bedrooms; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public String getEntryDate() { return entryDate; }

    public void setEntryDate(String entryDate) { this.entryDate = entryDate; }

    public String getSoldDate() { return soldDate; }

    public void setSoldDate(String soldDate) { this.soldDate = soldDate; }

    public long getAgentId() { return agentId; }

    public void setAgentId(long agentId) { this.agentId = agentId; }

    public String getAgentName() { return agentName; }

    public void setAgentName(String agentName) { this.agentName = agentName; }

    public String getAgentPicture() { return agentPicture; }

    public void setAgentPicture(String agentPicture) { this.agentPicture = agentPicture; }

    public Boolean getSchool() { return school; }

    public void setSchool(Boolean school) { this.school = school; }

    public Boolean getHospital() { return hospital; }

    public void setHospital(Boolean hospital) { this.hospital = hospital; }

    public Boolean getSupermarket() { return supermarket; }

    public void setSupermarket(Boolean supermarket) { this.supermarket = supermarket; }

    public Boolean getGolf() { return golf; }

    public  void setGolf(Boolean golf) { this.golf = golf; }

    public Boolean getPark() { return park; }

    public void setPark(Boolean park) { this.park = park; }

    public Boolean getCasino() { return casino; }

    public void setCasino(Boolean casino) { this.casino = casino; }

    public Double getLatitude() { return latitude; }

    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }

    public void setLongitude(Double longitude) { this.longitude = longitude; }
}
