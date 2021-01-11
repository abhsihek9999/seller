package com.abhishek.seller.login.presentation.viewmodel;

import android.util.Log;

import com.abhishek.seller.R;
import com.abhishek.seller.core.application.HKApp;
import com.abhishek.seller.core.common.FieldValidateStatus;
import com.abhishek.seller.core.common.MessgeNotifyStatus;
import com.abhishek.seller.core.common.ResponseApi;
import com.abhishek.seller.core.common.Status;
import com.abhishek.seller.login.data.model.request.VerifyNumber;
import com.abhishek.seller.login.domain.LoginDbUseCase;
import com.abhishek.seller.login.domain.LoginRemoteUseCase;
import com.abhishek.seller.login.domain.LoginUseCase;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {
    private LoginUseCase loginUseCase;
    private LoginRemoteUseCase loginRemoteUsecase;
    private LoginDbUseCase loginDbUseCase;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MutableLiveData<String> getCountryCode() {
        return countryCode;
    }

    public MutableLiveData<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(MutableLiveData<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public MutableLiveData<String> phoneNumber = new MutableLiveData<>();
    public MutableLiveData<String> countryCode = new MutableLiveData<>();
    private MutableLiveData<MessgeNotifyStatus> notifyLiveData = new MutableLiveData<>();
    public MutableLiveData<ResponseApi> serviceLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> showProgress = new MutableLiveData();
    public MutableLiveData<String> btnNextText = new MutableLiveData<>();


    public LoginViewModel(LoginUseCase loginUseCase, LoginDbUseCase loginDbUseCase, LoginRemoteUseCase loginRemoteUseCase) {
        this.loginUseCase = loginUseCase;
        this.loginRemoteUsecase = loginRemoteUseCase;
        this.loginDbUseCase = loginDbUseCase;
    }

    /*login btn clicked validate fields*/
    public void onNextClick() {
        Log.i("click", "working");
        getCompositeDisposable()
                .add(loginUseCase.validatePhoneandCountryCode(countryCode.getValue(), phoneNumber.getValue())
                        .subscribeWith(new DisposableSingleObserver<FieldValidateStatus>() {
                            @Override
                            public void onSuccess(FieldValidateStatus fieldValidateStatus) {
                                onValidateLoginResult(fieldValidateStatus);
                            }

                            @Override
                            public void onError(Throwable e) {
                                notifyLiveData.setValue(new MessgeNotifyStatus(Status.DEFAULT_MSG, ""));
                            }
                        }));

    }

    private void onValidateLoginResult(FieldValidateStatus fieldValidateStatus) {
        if (fieldValidateStatus.isValid()) {
            Disposable disposable = loginRemoteUsecase.isAvailInternet().subscribe(hasInternet -> {
                if (hasInternet) {
                    callVerifyPhoneNumber();
                } else {
                    serviceLiveData.setValue(new ResponseApi(Status.NO_INTERNET, HKApp.getAppContext().getString(R.string.internet_check), Status.NO_INTERNET));
                }

            });
            getCompositeDisposable().add(disposable);
        } else {
            notifyLiveData.setValue(new MessgeNotifyStatus(fieldValidateStatus.getStatus(), fieldValidateStatus.getMsg()));

        }

    }

    public void countryCodeClicked() {
        sendCommonCallBack(Status.COUNTRY_PICKER, "");

    }

    private void sendCommonCallBack(Status status, Object data) {

        notifyLiveData.setValue(new MessgeNotifyStatus(status, data));
    }

    private void callVerifyPhoneNumber() {
        VerifyNumber verifyNumberreq = new VerifyNumber(countryCode.getValue(), phoneNumber.getValue());
        compositeDisposable.add(loginRemoteUsecase.getOtp(verifyNumberreq)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        serviceLiveData.setValue(ResponseApi.loading(null));

                    }
                }).subscribe(new Consumer<ResponseApi>() {
                    @Override
                    public void accept(ResponseApi responseApi) throws Exception {
                        serviceLiveData.setValue(responseApi);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        serviceLiveData.setValue(ResponseApi.fail(HKApp.getAppContext().getResources().getString(R.string.retrofit_error), null));

                    }
                }));
    }

    public MutableLiveData<ResponseApi> serviceLiveData() {
        return serviceLiveData;

    }

    public MutableLiveData<MessgeNotifyStatus> getNotifyLiveData() {
        return notifyLiveData;

    }

    /*composit disposable*/
    private CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable != null) {
            return compositeDisposable;
        }
        return new CompositeDisposable();
    }

    @Override
    public void onCleared() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        super.onCleared();

    }


}
