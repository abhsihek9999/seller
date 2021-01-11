package com.abhishek.seller.login.domain;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.abhishek.seller.R;
import com.abhishek.seller.core.application.HKApp;
import com.abhishek.seller.core.common.NetworkUtil;
import com.abhishek.seller.core.common.ResponseApi;
import com.abhishek.seller.core.common.Status;
import com.abhishek.seller.core.network.ErrorResponseModel;
import com.abhishek.seller.core.network.NetworkUseCase;
import com.abhishek.seller.login.data.model.request.ProfileUpdate;
import com.abhishek.seller.login.data.model.request.VerifyNumber;
import com.abhishek.seller.login.data.model.response.LoginOTPResponse;
import com.abhishek.seller.login.data.model.response.OTPVerifyResponse;
import com.abhishek.seller.login.data.repository.LoginRepo;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import retrofit2.Response;

import static com.abhishek.seller.core.common.AppConstant.ResponseConstatnt.RES_200;
import static com.abhishek.seller.core.common.AppConstant.ResponseConstatnt.RES_400;
import static com.abhishek.seller.core.common.AppConstant.ResponseConstatnt.RES_401;
import static com.abhishek.seller.core.common.AppConstant.ResponseConstatnt.RES_FAIL;
import static com.abhishek.seller.core.common.Status.EDIT_PROFILE;
import static com.abhishek.seller.core.common.Status.GET_LOGIN_PIN;
import static com.abhishek.seller.core.common.Status.GET_NUMBER_VERIFIED;

public class LoginRemoteUseCase extends NetworkUseCase {
    LoginRepo.LoginRemoteInteractor loginRemoteInteractor;
    Gson gson;

    public LoginRemoteUseCase(LoginRepo.LoginRemoteInteractor loginRemoteInteractor, Gson gson) {
        this.loginRemoteInteractor = loginRemoteInteractor;
        this.gson = gson;
    }

    @Override
    public Single<Boolean> isAvailInternet() {
        return NetworkUtil.hasInternetConnection();
    }

/*
    response 200 handling
*/

    @Override
    public ResponseApi response200(Response<JsonObject> response, Status status) {
        if (status == GET_LOGIN_PIN) {
            LoginOTPResponse verifyNumber = gson.fromJson(response.body(), LoginOTPResponse.class);
            return ResponseApi.success(verifyNumber, status);
        } else if (status == GET_NUMBER_VERIFIED) {
            OTPVerifyResponse otpVerifyResponse = gson.fromJson(response.body(), OTPVerifyResponse.class);
            return ResponseApi.success(otpVerifyResponse, status);
        } else if (status == EDIT_PROFILE) {
            ProfileUpdate profileUpdate = gson.fromJson(response.body(), ProfileUpdate.class);
            return ResponseApi.success(profileUpdate, status);
        }
        return responseFail(status);

    }


    @Override
    public ResponseApi response401(Status apiTypestatus) {
        return ResponseApi.authFail(401, apiTypestatus);
    }

    @Override
    public ResponseApi responseFail400(Response<JsonObject> response, Status status) {
        try {
            String errorJson = response.errorBody().string();
            ErrorResponseModel responseModel = gson.fromJson(errorJson, ErrorResponseModel.class);
            return ResponseApi.fail(responseModel.getMessage(), status);
           /* String errorJson = response.errorBody().string();
            String errorMessage = AppUtil.errorMessageHandler(HKApp.getAppContext().getString(R.string.default_error), errorJson);
            return ResponseApi.fail(errorMessage, status);*/
        } catch (Exception e) {
            return ResponseApi.fail(HKApp.getAppContext().getResources().getString(R.string.default_error), status);
        }
    }

    @Override
    public ResponseApi responseFail(Status status) {
        return ResponseApi.fail(HKApp.getAppContext().getString(R.string.default_error), status);

    }

    public Single<ResponseApi> getOtp(VerifyNumber verifyNumberreq) {
        return loginRemoteInteractor.getOtptoLogin(verifyNumberreq).map(new Function<Response<JsonObject>, ResponseApi>() {
            @Override
            public ResponseApi apply(Response<JsonObject> response) throws Exception {
                if (response != null) {
                    return handleResponse(response, GET_LOGIN_PIN);
                } else {
                    return responseFail(GET_LOGIN_PIN);
                }
            }
        });
    }

    private ResponseApi handleResponse(Response<JsonObject> response, Status apiTypestatus) {

        switch (response.code()) {

            case RES_200:
                return response200(response, apiTypestatus);
            case RES_401:
                return response401(apiTypestatus);
            case RES_400:
                return responseFail400(response, apiTypestatus);
            case RES_FAIL:
                return responseFail(apiTypestatus);
            default:
                return responseFail(apiTypestatus);
        }
    }


    public Single<ResponseApi> getVerifyOtp(VerifyNumber verifyNumberreq) {
        return loginRemoteInteractor.getVerifyOtpLohin(verifyNumberreq).map(new Function<Response<JsonObject>, ResponseApi>() {
            @Override
            public ResponseApi apply(Response<JsonObject> response) throws Exception {
                if (response != null) {
                    return handleResponse(response, GET_NUMBER_VERIFIED);
                } else {
                    return responseFail(GET_NUMBER_VERIFIED);
                }
            }
        });
    }


    public Single<ResponseApi> getUpdateaprofile(ProfileUpdate profileUpdate) {
        return loginRemoteInteractor.getUpdateInfo(profileUpdate).map(new Function<Response<JsonObject>, ResponseApi>() {
            @Override
            public ResponseApi apply(Response<JsonObject> response) throws Exception {
                if (response != null) {
                    return handleResponse(response, EDIT_PROFILE);
                } else {
                    return responseFail(EDIT_PROFILE);
                }
            }
        });
    }

}
