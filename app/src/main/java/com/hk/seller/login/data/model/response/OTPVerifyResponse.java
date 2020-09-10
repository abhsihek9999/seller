package com.hk.seller.login.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTPVerifyResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("payload")
    @Expose
    private Payload payload;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }


    public class Payload {

        @SerializedName("name")
        @Expose
        private Object name;
        @SerializedName("profile_image")
        @Expose
        private Object profileImage;
        @SerializedName("email")
        @Expose
        private Object email;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("token")
        @Expose
        private String token;
        @SerializedName("isNewUser")
        @Expose
        private Boolean isNewUser;

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public Object getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(Object profileImage) {
            this.profileImage = profileImage;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Boolean getIsNewUser() {
            return isNewUser;
        }

        public void setIsNewUser(Boolean isNewUser) {
            this.isNewUser = isNewUser;
        }

    }
}
