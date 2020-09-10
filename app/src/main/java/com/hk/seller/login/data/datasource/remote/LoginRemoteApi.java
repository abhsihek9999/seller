package com.hk.seller.login.data.datasource.remote;

import com.google.gson.JsonObject;
import com.hk.seller.core.network.URLConstant;
import com.hk.seller.login.data.model.request.ProfileUpdate;
import com.hk.seller.login.data.model.request.VerifyNumber;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface LoginRemoteApi {
    @POST(URLConstant.GET_OTP_LOGIN)
    Single<Response<JsonObject>> getOpt(@HeaderMap Map<String, String> headers, @Body VerifyNumber verifyNumberreq);

    @POST(URLConstant.VERIFY_OTP_LOGIN)
    Single<Response<JsonObject>> getLoginViaOtp(@HeaderMap Map<String, String> headers, @Body VerifyNumber verifyNumberreq);

    @POST(URLConstant.UPDATE_PROFILE)
    Single<Response<JsonObject>> getUpdateProfile(@HeaderMap Map<String, String> headers, @Body ProfileUpdate profileUpdate);
}
