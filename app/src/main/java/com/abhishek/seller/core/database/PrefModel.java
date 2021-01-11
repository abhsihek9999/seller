package com.abhishek.seller.core.database;

import java.io.Serializable;

public class PrefModel implements Serializable {
    private String accessToken;
    private boolean loggedInMode;

    public boolean getLoggedInMode() {
        return loggedInMode;
    }

    public void setLoggedInMode(boolean loggedInMode) {
        this.loggedInMode = loggedInMode;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


//
//    private boolean isTour;
//    private boolean isLogin;
//    private String userLoginId;
//    private String userCountry;
//    private String countryNameCode;
//    private String countryPhoneCode;
//
//    private String currentLatitude;
//    private String currentLongitude;
//    private boolean isEmail;
//    private String signupPrefilledData;
//
//    private String emailId;
//
//    private String deviceToken;
//
//    private String updateCountryNameCode;
//    private String updateMobileNum;
//    private String updateEmailId;
//
//    // Verify Number Response
//    private VerifyNumberRes verifyNumberRes;
//    // Verify Login Response
//    private String accessToken;
//    private String tokenType;
//    private String refreshToken;
//    private int expiresIn;
//    private String scope;
//    private String message;
//    private String profileTimestamp;
//    private int count;
//    private boolean customerStatus;
//
//
//    private ProfileRes profileRes;
//    private String profilePathLocal;
//    private String profileComplpetePath;
//
//    private String profileHalfPath;
//
//    private String firstName;
//    private String lastName;
//
//    private String profileCompleted;
//    private boolean isRefreshApiCalled;
//
//    private Integer memberid;
//    private String tier;
//    private String points;
//    private long pointBalance;
//    private double pointBalanceWorth;
//    private String currencyCode;
//    private String isTenant;
//    private String isEmployee;
//    private String isMember;
//    private String notificationCount;
//    private File imageFile;
//    private boolean isComeFromCameraSetting;
//    private boolean isGolfExplore;
//
//    public String getNotificationCount() {
//        return notificationCount;
//    }
//
//    public void setNotificationCount(String notificationCount) {
//        this.notificationCount = notificationCount;
//    }
//
//    public Integer getMemberid() {
//        return memberid;
//    }
//
//    public void setMemberid(Integer memberid) {
//        this.memberid = memberid;
//    }
//
//    public String getTier() {
//        return tier;
//    }
//
//    public void setTier(String tier) {
//        this.tier = tier;
//    }
//
//    public String getPoints() {
//        return points;
//    }
//
//    public void setPoints(String points) {
//        this.points = points;
//    }
//
//    public String getCurrencyCode() {
//        return currencyCode;
//    }
//
//    public void setCurrencyCode(String currencyCode) {
//        this.currencyCode = currencyCode;
//    }
//
//    public String getIsTenant() {
//        return isTenant;
//    }
//
//    public void setIsTenant(String isTenant) {
//        this.isTenant = isTenant;
//    }
//
//    public String getIsEmployee() {
//        return isEmployee;
//    }
//
//    public void setIsEmployee(String isEmployee) {
//        this.isEmployee = isEmployee;
//    }
//
//    public String getIsMember() {
//        return isMember;
//    }
//
//    public void setIsMember(String isMember) {
//        this.isMember = isMember;
//    }
//
//    public double getPointBalanceWorth() {
//        return pointBalanceWorth;
//    }
//
//    public void setPointBalanceWorth(double pointBalanceWorth) {
//        this.pointBalanceWorth = pointBalanceWorth;
//    }
//
//    public String getProfileHalfPath() {
//        return profileHalfPath;
//    }
//
//    public void setProfileHalfPath(String profileHalfPath) {
//        this.profileHalfPath = profileHalfPath;
//    }
//
//    public String getProfileComplpetePath() {
//        return profileComplpetePath;
//    }
//
//    public void setProfileComplpetePath(String profileComplpetePath) {
//        this.profileComplpetePath = profileComplpetePath;
//    }
//
//    public String getProfilePathLocal() {
//        return profilePathLocal;
//    }
//
//    public void setProfilePathLocal(String profilePathLocal) {
//        this.profilePathLocal = profilePathLocal;
//    }
//
//    public String getProfileTimestamp() {
//        return profileTimestamp;
//    }
//
//    public void setProfileTimestamp(String profileTimestamp) {
//        this.profileTimestamp = profileTimestamp;
//    }
//
//    public int getCount() {
//        return count;
//    }
//
//    public void setCount(int count) {
//        this.count = count;
//    }
//
//    public boolean isCustomerStatus() {
//        return customerStatus;
//    }
//
//    public void setCustomerStatus(boolean customerStatus) {
//        this.customerStatus = customerStatus;
//    }
//
//    public String getAccessToken() {
//        return accessToken;
//    }
//
//    public void setAccessToken(String accessToken) {
//        this.accessToken = accessToken;
//    }
//
//    public String getTokenType() {
//        return tokenType;
//    }
//
//    public void setTokenType(String tokenType) {
//        this.tokenType = tokenType;
//    }
//
//    public String getRefreshToken() {
//        return refreshToken;
//    }
//
//    public void setRefreshToken(String refreshToken) {
//        this.refreshToken = refreshToken;
//    }
//
//    public Integer getExpiresIn() {
//        return expiresIn;
//    }
//
//    public void setExpiresIn(Integer expiresIn) {
//        this.expiresIn = expiresIn;
//    }
//
//    public String getScope() {
//        return scope;
//    }
//
//    public void setScope(String scope) {
//        this.scope = scope;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public boolean isTour() {
//        return isTour;
//    }
//
//    public void setTour(boolean tour) {
//        isTour = tour;
//    }
//
//    public String getDeviceToken() {
//        return deviceToken;
//    }
//
//    public void setDeviceToken(String deviceToken) {
//        this.deviceToken = deviceToken;
//    }
//
//    public VerifyNumberRes getVerifyNumberRes() {
//        return verifyNumberRes;
//    }
//
//    public void setVerifyNumberRes(VerifyNumberRes verifyNumberRes) {
//        this.verifyNumberRes = verifyNumberRes;
//    }
//
//
//
//    public boolean isLogin() {
//        return isLogin;
//    }
//
//    public String getSignupPrefilledData() {
//        return signupPrefilledData;
//    }
//
//    public void setSignupPrefilledData(String signupPrefilledData) {
//        this.signupPrefilledData = signupPrefilledData;
//    }
//
//    public boolean isEmail() {
//        return isEmail;
//    }
//
//    public void setEmail(boolean email) {
//        isEmail = email;
//    }
//
//    public void setLogin(boolean login) {
//        isLogin = login;
//    }
//
//    public String getUserCountry() {
//        return userCountry;
//    }
//
//    public void setUserCountry(String userCountry) {
//        this.userCountry = userCountry;
//    }
//
//    public String getCountryNameCode() {
//        return countryNameCode;
//    }
//
//    public void setCountryNameCode(String countryNameCode) {
//        this.countryNameCode = countryNameCode;
//    }
//
//    public String getCountryPhoneCode() {
//        return AppUtil.decryptData(countryPhoneCode);
//    }
//
//    public void setCountryPhoneCode(String countryPhoneCode) {
//        this.countryPhoneCode =  AppUtil.encryptData(countryPhoneCode);
//    }
//
//
//    public String getCurrentLatitude() {
//        return currentLatitude;
//    }
//
//    public void setCurrentLatitude(String currentLatitude) {
//        this.currentLatitude = currentLatitude;
//    }
//
//    public String getCurrentLongitude() {
//        return currentLongitude;
//    }
//
//    public void setCurrentLongitude(String currentLongitude) {
//        this.currentLongitude = currentLongitude;
//    }
//
//    public String getUserLoginId() {
//        return AppUtil.decryptData(userLoginId);
//    }
//
//    public void setUserLoginId(String userLoginId) {
//        this.userLoginId = AppUtil.encryptData(userLoginId);
//    }
//
//
//    public String getEmailId() {
//        return AppUtil.decryptData(emailId);
//    }
//
//    public void setEmailId(String emailId) {
//        this.emailId = emailId;
//    }
//
//    public String getUpdateCountryNameCode() {
//        return updateCountryNameCode;
//    }
//
//    public void setUpdateCountryNameCode(String updateCountryNameCode) {
//        this.updateCountryNameCode = updateCountryNameCode;
//    }
//
//    public String getUpdateMobileNum() {
//        return updateMobileNum;
//    }
//
//    public void setUpdateMobileNum(String updateMobileNum) {
//        this.updateMobileNum = updateMobileNum;
//    }
//
//    public String getUpdateEmailId() {
//        return updateEmailId;
//    }
//
//    public void setUpdateEmailId(String updateEmailId) {
//        this.updateEmailId = updateEmailId;
//    }
//
//    public ProfileRes getProfileRes() {
//        return profileRes;
//    }
//
//    public void setProfileRes(ProfileRes profileRes) {
//        this.profileRes = profileRes;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public boolean isGolfExplore() {
//        return isGolfExplore;
//    }
//
//    public void setGolfExplore(boolean golfExplore) {
//        isGolfExplore = golfExplore;
//    }
//
//    public long getPointBalance() {
//        return pointBalance;
//    }
//
//    public void setPointBalance(long pointBalance) {
//        this.pointBalance = pointBalance;
//    }
//
//    public File getImageFile() {
//        return imageFile;
//    }
//
//    public void setImageFile(File imageFile) {
//        this.imageFile = imageFile;
//    }
//
//    public boolean isComeFromCameraSetting() {
//        return isComeFromCameraSetting;
//    }
//
//    public void setComeFromCameraSetting(boolean comeFromCameraSetting) {
//        isComeFromCameraSetting = comeFromCameraSetting;
//    }
//
//    public String getProfileCompleted() {
//        return profileCompleted;
//    }
//
//    public void setProfileCompleted(String profileCompleted) {
//        this.profileCompleted = profileCompleted;
//    }
//
//    public boolean isRefreshApiCalled() {
//        return isRefreshApiCalled;
//    }
//
//    public void setRefreshApiCalled(boolean refreshApiCalled) {
//        isRefreshApiCalled = refreshApiCalled;
//    }
}