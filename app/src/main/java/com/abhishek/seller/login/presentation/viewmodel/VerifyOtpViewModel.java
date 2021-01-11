package com.abhishek.seller.login.presentation.viewmodel;

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

import static com.abhishek.seller.core.common.Status.EDIT_PHONE_NUMBER;

public class VerifyOtpViewModel extends ViewModel {
    private LoginUseCase loginUseCase;
    private LoginRemoteUseCase loginRemoteUsecase;
    private LoginDbUseCase loginDbUseCase;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public MutableLiveData<String> otp = new MutableLiveData<>();
    private MutableLiveData<MessgeNotifyStatus> notifyLiveData = new MutableLiveData<>();
    public MutableLiveData<ResponseApi> serviceLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> showProgress = new MutableLiveData<>();
    public MutableLiveData<String> phoneNumber = new MutableLiveData<>();
    public MutableLiveData<String> btnNextText = new MutableLiveData<>();
    public MutableLiveData<String> countryCode = new MutableLiveData<>();
    public MutableLiveData<String> FCM_TOKEN = new MutableLiveData<>();
    public MutableLiveData<String> phonewithCountry = new MutableLiveData<>();

    public MutableLiveData<String> getPhoneNumber() {
        return phoneNumber;
    }


    public MutableLiveData<String> getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(MutableLiveData<String> countryCode) {
        this.countryCode = countryCode;
    }

    public MutableLiveData<String> getFCM_TOKEN() {
        return FCM_TOKEN;
    }

    public void setFCM_TOKEN(MutableLiveData<String> FCM_TOKEN) {
        this.FCM_TOKEN = FCM_TOKEN;
    }

    public VerifyOtpViewModel(LoginUseCase loginUseCase, LoginDbUseCase loginDbUseCase, LoginRemoteUseCase loginRemoteUseCase) {
        this.loginUseCase = loginUseCase;
        this.loginRemoteUsecase = loginRemoteUseCase;
        this.loginDbUseCase = loginDbUseCase;
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


    public MutableLiveData<ResponseApi> serviceLiveData() {
        return serviceLiveData;

    }

    public MutableLiveData<MessgeNotifyStatus> getNotifyLiveData() {
        return notifyLiveData;

    }

    public void verifyOtp() {
        getCompositeDisposable()
                .add(loginUseCase.validateOTP(otp.getValue())
                        .subscribeWith(new DisposableSingleObserver<FieldValidateStatus>() {
                            @Override
                            public void onSuccess(FieldValidateStatus fieldValidateStatus) {
                                onValidateOTPResult(fieldValidateStatus);
                            }

                            @Override
                            public void onError(Throwable e) {
                                notifyLiveData.setValue(new MessgeNotifyStatus(Status.DEFAULT_MSG, ""));
                            }
                        }));
    }

    private void onValidateOTPResult(FieldValidateStatus fieldValidateStatus) {
        if (fieldValidateStatus.isValid()) {
            Disposable disposable = loginRemoteUsecase.isAvailInternet().subscribe(hasInternet -> {
                if (hasInternet) {
                    callVerifyOTP();
                } else {
                    serviceLiveData.setValue(new ResponseApi(Status.NO_INTERNET, HKApp.getAppContext().getString(R.string.internet_check), Status.NO_INTERNET));
                }

            });
            getCompositeDisposable().add(disposable);
        } else {
            notifyLiveData.setValue(new MessgeNotifyStatus(fieldValidateStatus.getStatus(), fieldValidateStatus.getMsg()));

        }
    }

    private void callVerifyOTP() {
        VerifyNumber verifyNumberreq = new VerifyNumber(getCountryCode().getValue(), getPhoneNumber().getValue(), otp.getValue(), getFCM_TOKEN().getValue());
        compositeDisposable.add(loginRemoteUsecase.getVerifyOtp(verifyNumberreq)
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

    public void editPhoneNumber() {
        sendCommonCallBack(EDIT_PHONE_NUMBER, "");
    }

    public void resendOTP() {
        Disposable disposable = loginRemoteUsecase.isAvailInternet().subscribe(hasInternet -> {
            if (hasInternet) {
                callResendOTP();
            } else {
                serviceLiveData.setValue(new ResponseApi(Status.NO_INTERNET, HKApp.getAppContext().getString(R.string.internet_check), Status.NO_INTERNET));
            }

        });
        getCompositeDisposable().add(disposable);


    }

    private void callResendOTP() {
        VerifyNumber verifyNumberreq = new VerifyNumber(getCountryCode().getValue(), getPhoneNumber().getValue());
        compositeDisposable.add(loginRemoteUsecase.getOtp(verifyNumberreq)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

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

    private void sendCommonCallBack(Status status, Object data) {

        notifyLiveData.setValue(new MessgeNotifyStatus(status, data));
    }

}

