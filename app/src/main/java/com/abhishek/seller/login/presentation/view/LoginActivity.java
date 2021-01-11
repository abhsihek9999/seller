package com.abhishek.seller.login.presentation.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.HK.countrypicker.Country;
import com.HK.countrypicker.CountryPicker;
import com.HK.countrypicker.listeners.OnCountryPickerListener;
import com.abhishek.seller.R;
import com.abhishek.seller.core.application.HKApp;
import com.abhishek.seller.core.application.base_component.BaseActivity;
import com.abhishek.seller.core.application.di.ViewModelFactory;
import com.abhishek.seller.core.common.AppConstant;
import com.abhishek.seller.core.common.Status;
import com.abhishek.seller.databinding.ActivityLoginBinding;
import com.abhishek.seller.login.data.model.response.LoginOTPResponse;
import com.abhishek.seller.login.presentation.viewmodel.LoginViewModel;
import com.abhishek.seller.utils.ViewUtils;
import com.rw.keyboardlistener.KeyboardUtils;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class LoginActivity extends BaseActivity implements OnCountryPickerListener {
    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Inject
    @Named("LoginActivity")
    ViewModelFactory viewModelFactory;

    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;
    private CountryPicker countryPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayout());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        init();
    }

    @Override
    protected void init() {
        HKApp.getAppContext().getAppComponent().doInjection(this);
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
        binding.setViewModel(loginViewModel);
        binding.setActivity(this);
        binding.setLifecycleOwner(this);
        setListener();
        progressHandler(false, null);
        intitDatePicker();
        KeyboardUtils.addKeyboardToggleListener(this, new KeyboardUtils.SoftKeyboardToggleListener() {
            @Override
            public void onToggleSoftKeyboard(boolean isVisible) {
                if (isVisible) {

                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) binding.loginModule.constraintLayout.getLayoutParams();
                    params.verticalBias = 0.7f; // here is one modification for example. modify anything else you want :)
                    binding.loginModule.constraintLayout.setLayoutParams(params); // request the view to use the new modified params

                } else {
                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) binding.loginModule.constraintLayout.getLayoutParams();
                    params.verticalBias = 1f; // here is one modification for example. modify anything else you want :)
                    binding.loginModule.constraintLayout.setLayoutParams(params); // request the view to use the new modified params

                }

            }
        });

    }

    private void intitDatePicker() {
        countryPicker = new CountryPicker.Builder().with(this).theme(R.style.Picker).canSearch(true).listener(this).build();
        if (countryPicker.getCountryFromSIM() != null) {
            setCountryCode(countryPicker.getCountryFromSIM().getFlag(), countryPicker.getCountryFromSIM().getDialCode());
        } else {
            setCountryCode(R.drawable.flag_ar, AppConstant.DEFAUT_COUNTRY_CODE);
        }

        binding.loginModule.phcode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    v.performClick();
                }
            }
        });

    }

    private void setCountryCode(int flag, String dialCode) {
        binding.loginModule.imFlagImage.setImageResource(flag);
        binding.loginModule.phcode.setText(dialCode);
        loginViewModel.countryCode.setValue(dialCode);
    }

    private void progressHandler(boolean isProgress, Status status) {
        if (isProgress) {
            loginViewModel.showProgress.setValue(VISIBLE);
            loginViewModel.btnNextText.setValue("");
            binding.loginModule.tvContinue.setClickable(false);

        } else {
            loginViewModel.showProgress.setValue(INVISIBLE);
            loginViewModel.btnNextText.setValue(getResources().getString(R.string.tv_continue));
            binding.loginModule.tvContinue.setClickable(true);
        }
    }

    private void observeNotifyField() {
        loginViewModel.getNotifyLiveData().observe(this, messgeNotifyStatus -> {
            ViewUtils.hideKeyboard(this);
            switch (messgeNotifyStatus.getStatus()) {
                case BACK_CLICK:
                    finish();
                    break;
                case COUNTRY_PICKER:
                    showCountryPicker();
                    break;
              /*  case NO_INTERNET:
                    ViewUtils.showSnackBar(binding.getRoot(), getString(R.string.internet_check), getString(R.string.error));
                    break;
*/
                case ERROR_MOBILE:
                    ViewUtils.showSnackBar(binding.getRoot(), messgeNotifyStatus.getData().toString(), getString(R.string.incomplete_data));
                    break;

                case ERROR_MOBILE_ZERO:
                    ViewUtils.showSnackBar(binding.getRoot(), messgeNotifyStatus.getData().toString(), getString(R.string.incomplete_data));
                    break;
                default:
                    break;
            }
        });
    }

    private void showCountryPicker() {
        countryPicker.showBottomSheet(this); // Show the dialog

    }

    private void observeServiceResponse() {
        loginViewModel.serviceLiveData.observe(this, response -> {
            ViewUtils.hideKeyboard(this);
            switch (response.status) {
                case LOADING:
                    progressHandler(true, null);
                    break;
                case NO_INTERNET:
                    ViewUtils.showSnackBar(binding.getRoot(), getString(R.string.internet_check), getString(R.string.error));
                    break;
                case SUCCESS:
                    progressHandler(false, null);
                    LoginOTPResponse loginOTPResponse = ((LoginOTPResponse) response.data);
                    if (loginOTPResponse != null) {
                        loginOTPResponse.setCountryCode(loginViewModel.countryCode.getValue());
                        loginOTPResponse.setPhoneNumber(loginViewModel.phoneNumber.getValue());
                        openOtpActivity(loginOTPResponse);
                    }


                    break;
                case FAIL:
                    progressHandler(false, null);
                    ViewUtils.showSnackBar(binding.getRoot(), response.data != null ? response.data.toString() : getString(R.string.default_error), getString(R.string.connection));
                    break;
                case FAIL_400:
                    progressHandler(false, null);
                    ViewUtils.showSnackBar(binding.getRoot(), (String) response.data, getString(R.string.error));

                    break;
            }
        });
    }

    private void openOtpActivity(LoginOTPResponse loginOTPResponse) {
//        Intent intent = VerifyOtpActivity.getStartIntent(this);
//        intent.putExtra(AppConstant.USER_DATA, loginOTPResponse);
//        startActivity(intent);
    }

    @Override
    protected void setListener() {
        if (loginViewModel != null && loginViewModel.serviceLiveData().hasObservers()) {
            loginViewModel.serviceLiveData().removeObservers(this);

        } else {
            observeServiceResponse();
        }

        if (loginViewModel != null && loginViewModel.getNotifyLiveData().hasObservers()) {
            loginViewModel.getNotifyLiveData().removeObservers(this);

        } else {
            observeNotifyField();
        }

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }


    @Override
    public void onSelectCountry(Country country) {
        setCountryCode(country.getFlag(), country.getDialCode());

    }
}
