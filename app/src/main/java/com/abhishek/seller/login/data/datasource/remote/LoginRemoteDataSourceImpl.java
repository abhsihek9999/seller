package com.abhishek.seller.login.data.datasource.remote;

import com.google.gson.JsonObject;
import com.abhishek.seller.core.database.AppPref;
import com.abhishek.seller.login.data.model.request.ProfileUpdate;
import com.abhishek.seller.login.data.model.request.VerifyNumber;
import com.abhishek.seller.login.data.repository.LoginRepo;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.Response;

import static com.abhishek.seller.core.common.AppConstant.AUTHORIZATION;

public class LoginRemoteDataSourceImpl implements LoginRepo.LoginRemoteInteractor {
    LoginRemoteApi loginRemoteApi;

    public LoginRemoteDataSourceImpl(LoginRemoteApi loginRemoteApi) {
        this.loginRemoteApi = loginRemoteApi;
    }

    @Override
    public Single<Response<JsonObject>> getOtptoLogin(VerifyNumber verifyNumberreq) {
        Map<String, String> headers = new HashMap<>();
        return loginRemoteApi.getOpt(headers, verifyNumberreq);
    }

    @Override
    public Single<Response<JsonObject>> getVerifyOtpLohin(VerifyNumber verifyNumberreq) {
        Map<String, String> headers = new HashMap<>();
        return loginRemoteApi.getLoginViaOtp(headers, verifyNumberreq);
    }

    @Override
    public Single<Response<JsonObject>> getUpdateInfo(ProfileUpdate profileUpdate) {
        Map<String, String> headers = new HashMap<>();
        headers.put(AUTHORIZATION, AppPref.INSTANCE.getModelInstance().getAccessToken());
        return loginRemoteApi.getUpdateProfile(headers, profileUpdate);
    }
}
