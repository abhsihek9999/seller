package com.abhishek.seller.login.data.model.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileResponse {
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
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("profile_image")
        @Expose
        private Object profileImage;
        @SerializedName("gender")
        @Expose
        private Object gender;
        @SerializedName("dob")
        @Expose
        private Object dob;
        @SerializedName("softDelete")
        @Expose
        private Boolean softDelete;
        @SerializedName("isActive")
        @Expose
        private Boolean isActive;
        @SerializedName("isRegister")
        @Expose
        private Boolean isRegister;
        @SerializedName("isVerifiedEmail")
        @Expose
        private Boolean isVerifiedEmail;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("country_code")
        @Expose
        private String countryCode;
        @SerializedName("contact_no")
        @Expose
        private String contactNo;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("mailToken")
        @Expose
        private String mailToken;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(Object profileImage) {
            this.profileImage = profileImage;
        }

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public Object getDob() {
            return dob;
        }

        public void setDob(Object dob) {
            this.dob = dob;
        }

        public Boolean getSoftDelete() {
            return softDelete;
        }

        public void setSoftDelete(Boolean softDelete) {
            this.softDelete = softDelete;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

        public Boolean getIsRegister() {
            return isRegister;
        }

        public void setIsRegister(Boolean isRegister) {
            this.isRegister = isRegister;
        }

        public Boolean getIsVerifiedEmail() {
            return isVerifiedEmail;
        }

        public void setIsVerifiedEmail(Boolean isVerifiedEmail) {
            this.isVerifiedEmail = isVerifiedEmail;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getContactNo() {
            return contactNo;
        }

        public void setContactNo(String contactNo) {
            this.contactNo = contactNo;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public String getMailToken() {
            return mailToken;
        }

        public void setMailToken(String mailToken) {
            this.mailToken = mailToken;
        }

    }
}
