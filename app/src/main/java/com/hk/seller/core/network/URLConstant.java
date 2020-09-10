package com.hk.seller.core.network;

public interface URLConstant {
    String BASE_URL = "http://hk_customer.bawsala.net/";
    String GET_OTP_LOGIN = BASE_URL + "api/v1/session/otp";
    String VERIFY_OTP_LOGIN = BASE_URL + "api/v1/session/login";
    String UPDATE_PROFILE = BASE_URL + "/api/v1/customer/edit-profile";
    String GET_CATEGORY = "http://demo9067541.mockable.io/categorylist";
}
