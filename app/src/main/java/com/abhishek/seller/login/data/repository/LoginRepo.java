package com.abhishek.seller.login.data.repository;

import com.google.gson.JsonObject;
import com.abhishek.seller.login.data.model.request.ProfileUpdate;
import com.abhishek.seller.login.data.model.request.VerifyNumber;

import io.reactivex.Single;
import retrofit2.Response;

public interface LoginRepo {


    interface LoginRemoteInteractor {
        Single<Response<JsonObject>> getOtptoLogin(VerifyNumber verifyNumberreq);

        Single<Response<JsonObject>> getVerifyOtpLohin(VerifyNumber verifyNumberreq);

        Single<Response<JsonObject>> getUpdateInfo(ProfileUpdate profileUpdate);
    }

    interface LoginDbInteractor {
    }


}
