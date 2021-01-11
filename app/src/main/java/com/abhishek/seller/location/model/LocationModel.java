package com.abhishek.seller.location.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LocationModel implements Serializable {
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("oldLatitude")
    @Expose
    private String oldLatitude;
    @SerializedName("oldLongitude")
    @Expose
    private String oldLongitude;

    public String getOldLatitude() {
        return oldLatitude;
    }

    public void setOldLatitude(String oldLatitude) {
        this.oldLatitude = oldLatitude;
    }

    public String getOldLongitude() {
        return oldLongitude;
    }

    public void setOldLongitude(String oldLongitude) {
        this.oldLongitude = oldLongitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
