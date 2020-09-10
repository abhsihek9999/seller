package com.hk.seller.login.data.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileUpdate {
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("email")
    @Expose
    String email;

    public ProfileUpdate(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
