package com.hk.seller.login.presentation.view;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.hk.seller.R;
import com.hk.seller.core.application.HKApp;
import com.hk.seller.core.application.base_component.BaseActivity;
import com.hk.seller.core.application.di.ViewModelFactory;
import com.hk.seller.core.common.AppConstant;
import com.hk.seller.core.common.Status;
import com.hk.seller.core.database.AppPref;
import com.hk.seller.core.database.PrefModel;
import com.hk.seller.databinding.ActivityVerifyOtpBinding;
import com.hk.seller.login.data.model.response.LoginOTPResponse;
import com.hk.seller.login.data.model.response.OTPVerifyResponse;
import com.hk.seller.login.presentation.viewmodel.VerifyOtpViewModel;
import com.hk.seller.utils.OTPReceiveListener;
import com.hk.seller.utils.SmsBroadcastReceiver;
import com.hk.seller.utils.ViewUtils;
import com.rw.keyboardlistener.KeyboardUtils;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class VerifyOtpActivity extends BaseActivity implements OTPReceiveListener {

    @Inject
    @Named("VerifyOtpActivity")
    ViewModelFactory viewModelFactory;
    private ActivityVerifyOtpBinding binding;
    private VerifyOtpViewModel verifyOtpViewModel;
    private SmsBroadcastReceiver mSmsBroadcastReceiver;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, VerifyOtpActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inside your activity (if you did not enable transitions in your theme)
        binding = DataBindingUtil.setContentView(this, getLayout());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        init();
        setListener();
        progressHandler(false, null);


    }

    @Override
    protected void init() {
        HKApp.getAppContext().getAppComponent().doInjection(this);
        verifyOtpViewModel = ViewModelProviders.of(this, viewModelFactory).get(VerifyOtpViewModel.class);
        binding.setViewModel(verifyOtpViewModel);
        binding.setLifecycleOwner(this);
        AlphaAnimation fadeIn = new AlphaAnimation(0.1f, 1.0f);
        fadeIn.setDuration(700);
        fadeIn.setFillAfter(true);
        binding.otpModule.otpField.startAnimation(fadeIn);
        binding.otpModule.tvOtpHeader.startAnimation(fadeIn);
        binding.otpModule.tvOtpSubheader.startAnimation(fadeIn);
        if (getIntent() != null) {
            LoginOTPResponse loginOTPResponse = (LoginOTPResponse) getIntent().getSerializableExtra(AppConstant.USER_DATA);
            if (loginOTPResponse != null) {
                verifyOtpViewModel.phoneNumber.setValue(loginOTPResponse.getPhoneNumber());
                verifyOtpViewModel.countryCode.setValue(loginOTPResponse.getCountryCode());
                String subTile = getResources().getString(R.string.otp_sub_heading) + " " + loginOTPResponse.getCountryCode() + " " + loginOTPResponse.getPhoneNumber();
                verifyOtpViewModel.phonewithCountry.setValue(subTile);
            }
        }
        KeyboardUtils.addKeyboardToggleListener(this, new KeyboardUtils.SoftKeyboardToggleListener() {
            @Override
            public void onToggleSoftKeyboard(boolean isVisible) {
                if (isVisible) {

                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) binding.otpModule.constraintLayout.getLayoutParams();
                    params.verticalBias = 0.7f; // here is one modification for example. modify anything else you want :)
                    binding.otpModule.constraintLayout.setLayoutParams(params); // request the view to use the new modified params

                } else {
                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) binding.otpModule.constraintLayout.getLayoutParams();
                    params.verticalBias = 1f; // here is one modification for example. modify anything else you want :)
                    binding.otpModule.constraintLayout.setLayoutParams(params); // request the view to use the new modified params

                }

            }
        });

        startSMSListener();
    }

    @Override
    protected void setListener() {
        if (verifyOtpViewModel != null && verifyOtpViewModel.serviceLiveData().hasObservers()) {
            verifyOtpViewModel.serviceLiveData().removeObservers(this);

        } else {
            observeServiceResponse();
        }

        if (verifyOtpViewModel != null && verifyOtpViewModel.getNotifyLiveData().hasObservers()) {
            verifyOtpViewModel.getNotifyLiveData().removeObservers(this);

        } else {
            observeNotifyField();
        }
    }

    private void observeNotifyField() {
        verifyOtpViewModel.getNotifyLiveData().observe(this, messgeNotifyStatus -> {
            ViewUtils.hideKeyboard(this);
            switch (messgeNotifyStatus.getStatus()) {
                case BACK_CLICK:
                    finish();
                    break;

                case ERROR_OTP:
                    ViewUtils.showSnackBar(binding.getRoot(), messgeNotifyStatus.getData().toString(), getString(R.string.incomplete_data));
                    break;
                case EDIT_PHONE_NUMBER:
                    openLoginActivity();
                    break;
                default:
                    break;
            }
        });
    }

    private void openLoginActivity() {
        finish();
    }

    private void progressHandler(boolean isProgress, Status status) {
        if (isProgress) {
            verifyOtpViewModel.showProgress.setValue(VISIBLE);
            verifyOtpViewModel.btnNextText.setValue("");
            binding.otpModule.tvContinue.setClickable(false);

        } else {
            verifyOtpViewModel.showProgress.setValue(INVISIBLE);
            verifyOtpViewModel.btnNextText.setValue(getResources().getString(R.string.tv_continue));
            binding.otpModule.tvContinue.setClickable(true);
        }
    }

    private void observeServiceResponse() {
        verifyOtpViewModel.serviceLiveData.observe(this, response -> {
            ViewUtils.hideKeyboard(this);
            switch (response.status) {
                case LOADING:
                    progressHandler(true, null);

                    break;
                case SUCCESS:
                    progressHandler(false, null);

                    switch (response.apiTypeStatus) {

                        case GET_LOGIN_PIN:
                            break;

                        case GET_NUMBER_VERIFIED:
                            OTPVerifyResponse otpVerifyResponse = (OTPVerifyResponse) response.data;
                            PrefModel prefModel = AppPref.INSTANCE.getModelInstance();
                            prefModel.setAccessToken(otpVerifyResponse.getPayload().getToken());
                            prefModel.setLoggedInMode(true);
                            AppPref.INSTANCE.setPref(prefModel);
                            openInfoActivity(otpVerifyResponse);
                            break;

                    }
                    break;
                case FAIL:
                    progressHandler(false, null);
                    ViewUtils.showSnackBar(binding.getRoot(), response.data != null ? response.data.toString() : getString(R.string.default_error), getString(R.string.error));
                    break;
                case FAIL_400:
                    progressHandler(false, null);
                    ViewUtils.showSnackBar(binding.getRoot(), (String) response.data, getString(R.string.error));
                    break;
                case NO_INTERNET:
                    ViewUtils.showSnackBar(binding.getRoot(), getString(R.string.internet_check), getString(R.string.error));
                    break;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onPause() {
        super.onPause();
        stopReciever();
    }

    public void startSMSListener() {
        SmsRetrieverClient mClient = SmsRetriever.getClient(this);
        Task<Void> mTask = mClient.startSmsRetriever();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                regiasterReciever();
            }
        });
        mTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

    private void regiasterReciever() {
        mSmsBroadcastReceiver = new SmsBroadcastReceiver();
        mSmsBroadcastReceiver.setOnOtpListeners(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
        registerReceiver(mSmsBroadcastReceiver, intentFilter);
    }


    private void openInfoActivity(OTPVerifyResponse otpVerifyResponse) {
        /*if User already exits navigate to Dashboard otherwise getInfo Activity*/
        if (otpVerifyResponse.getPayload().getIsNewUser()) {

            finish();
        } else {

            finish();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_verify_otp;
    }

    @Override
    public void onOTPReceived(String otp) {
        Log.i("otp", "recieved");
        verifyOtpViewModel.otp.setValue(otp);
        verifyOtpViewModel.verifyOtp();


    }

    private void stopReciever() {
        if (mSmsBroadcastReceiver != null) {
            unregisterReceiver(mSmsBroadcastReceiver);
        }
    }

    @Override
    public void onOTPTimeOut() {

    }
}
