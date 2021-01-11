package com.abhishek.seller.core.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.abhishek.seller.core.common.Status.AUTH_FAIL;
import static com.abhishek.seller.core.common.Status.DEFAULT_ERROR;
import static com.abhishek.seller.core.common.Status.ERROR;
import static com.abhishek.seller.core.common.Status.FAIL;
import static com.abhishek.seller.core.common.Status.FAIL_400;
import static com.abhishek.seller.core.common.Status.LOADING;
import static com.abhishek.seller.core.common.Status.NO_DATA;
import static com.abhishek.seller.core.common.Status.SUCCESS;


public class ResponseApi {

    public final Status status;
    public final Status apiTypeStatus;

    @Nullable
    public final Object data;



    public ResponseApi(Status status, @Nullable Object data,Status apiTypeStatus ) {
        this.status = status;
        this.data = data;
        this.apiTypeStatus = apiTypeStatus;
    }

    public static ResponseApi loading(Status apiTypeStatus) {
        return new ResponseApi(LOADING, null, apiTypeStatus);
    }

    public static ResponseApi success(@NonNull Object data, Status apiTypeStatus) {
        return new ResponseApi(SUCCESS, data, apiTypeStatus);
    }

    public static ResponseApi fail(@NonNull Object data, Status apiTypeStatus) {
        return new ResponseApi(FAIL, data, apiTypeStatus);
    }

    public static ResponseApi noData(@NonNull Object data, Status apiTypeStatus) {
        return new ResponseApi(NO_DATA, data, apiTypeStatus);
    }

    public static ResponseApi authFail(@NonNull Object data, Status apiTypeStatus) {
        return new ResponseApi(AUTH_FAIL, null, apiTypeStatus);
    }

    public static ResponseApi error(@NonNull Throwable error, Status apiTypeStatus) {
        return new ResponseApi(ERROR, null, apiTypeStatus);
    }

    public static ResponseApi defaultError(String error, Status apiTypeStatus) {
        return new ResponseApi(DEFAULT_ERROR, error, apiTypeStatus);
    }

    public static ResponseApi genericCallBack(Status status, Object data) {
        return new ResponseApi(status, data, null);
    }

    public static ResponseApi fail400(@NonNull Object data, Status apiTypeStatus) {
        return new ResponseApi(FAIL_400, data, apiTypeStatus);
    }

}
