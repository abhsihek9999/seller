package com.hk.seller.login.domain;

import android.text.TextUtils;

import com.hk.seller.R;
import com.hk.seller.core.application.HKApp;
import com.hk.seller.core.common.FieldValidateStatus;
import com.hk.seller.core.common.Status;
import com.hk.seller.utils.Validator;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

import static com.hk.seller.core.common.Status.EMPTY_EMAIL;
import static com.hk.seller.core.common.Status.EMPTY_NAME;
import static com.hk.seller.core.common.Status.ERROR_MOBILE;
import static com.hk.seller.core.common.Status.ERROR_MOBILE_ZERO;
import static com.hk.seller.core.common.Status.ERROR_OTP;

public class LoginUseCase {
    public Single<FieldValidateStatus> validatePhoneandCountryCode(String countryCode, String phoneNumber) {
        return Single.create(new SingleOnSubscribe<FieldValidateStatus>() {
            @Override
            public void subscribe(SingleEmitter<FieldValidateStatus> emitter) throws Exception {
                emitter.onSuccess(validLoginFields(countryCode, phoneNumber));
            }
        });
    }


    /*validate login fields*/
    private FieldValidateStatus validLoginFields(String countryCode, String phoneNumber) {
        if (countryCode == null || countryCode.isEmpty() || countryCode.contains("null")) {
            return new FieldValidateStatus(false, HKApp.getAppContext().getString(R.string.empty_country_code), ERROR_MOBILE);
        } else if (phoneNumber == null || phoneNumber.isEmpty() || phoneNumber.contains("null")) {
            return new FieldValidateStatus(false, HKApp.getAppContext().getString(R.string.empty_mobile_error), ERROR_MOBILE);
        } else if (!Validator.isPhoneNumberValid(String.valueOf(Long.parseLong(phoneNumber)), countryCode)) {
            return new FieldValidateStatus(false, HKApp.getAppContext().getString(R.string.error_invalid_mobile), ERROR_MOBILE);
        } else if (Validator.isStartWithZero(phoneNumber)) {
            return new FieldValidateStatus(false, HKApp.getAppContext().getString(R.string.error_invalid_mobile), ERROR_MOBILE_ZERO);
        }
        return new FieldValidateStatus(true, "", Status.SUCCESS);
    }

    public Single<FieldValidateStatus> validateOTP(String value) {

        return Single.create(new SingleOnSubscribe<FieldValidateStatus>() {
            @Override
            public void subscribe(SingleEmitter<FieldValidateStatus> emitter) throws Exception {
                emitter.onSuccess(validotpFields(value));
            }
        });
    }

    private FieldValidateStatus validotpFields(String textOTP) {

        if (TextUtils.isEmpty(textOTP) || textOTP.length() != 4) {
            return new FieldValidateStatus(false, HKApp.getAppContext().getString(R.string.otp_cannot_be_empty), ERROR_OTP);
        }
        return new FieldValidateStatus(true, "", Status.SUCCESS);

    }


    public Single<FieldValidateStatus> validateemail(String email, String name) {

        return Single.create(new SingleOnSubscribe<FieldValidateStatus>() {
            @Override
            public void subscribe(SingleEmitter<FieldValidateStatus> emitter) throws Exception {
                emitter.onSuccess(validemailFields(email, name));
            }
        });
    }

    private FieldValidateStatus validemailFields(String email, String name) {
        if (name == null || name.isEmpty() || name.contains("null")) {
            return new FieldValidateStatus(false, HKApp.getAppContext().getString(R.string.valid_name_required), EMPTY_NAME);
        }
        if (email == null || email.isEmpty() || !Validator.validateEmail(email)) {
            return new FieldValidateStatus(false, HKApp.getAppContext().getString(R.string.valid_email_required), EMPTY_EMAIL);
        }
        return new FieldValidateStatus(true, "", Status.SUCCESS);
    }
}
