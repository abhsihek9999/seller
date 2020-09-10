package com.hk.seller.core.network;

import com.google.gson.JsonObject;
import com.hk.seller.core.common.ResponseApi;
import com.hk.seller.core.common.Status;

import io.reactivex.Single;
import retrofit2.Response;

public abstract class NetworkUseCase {

    public abstract Single<Boolean> isAvailInternet();
    public abstract ResponseApi response200(Response<JsonObject> response, Status status);
    public abstract ResponseApi response401(Status apiTypestatus);
    public abstract ResponseApi responseFail400(Response<JsonObject> response,Status status);
    public abstract ResponseApi responseFail(Status status);


}