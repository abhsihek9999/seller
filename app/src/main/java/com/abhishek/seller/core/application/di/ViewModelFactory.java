package com.abhishek.seller.core.application.di;

import com.abhishek.seller.login.domain.LoginDbUseCase;
import com.abhishek.seller.login.domain.LoginRemoteUseCase;
import com.abhishek.seller.login.domain.LoginUseCase;
import com.abhishek.seller.login.presentation.viewmodel.LoginViewModel;
import com.abhishek.seller.login.presentation.viewmodel.VerifyOtpViewModel;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private LoginUseCase loginUseCase;
    private LoginDbUseCase loginDbUseCase;
    private LoginRemoteUseCase loginRemoteUseCase;

    public ViewModelFactory(Object objectone, Object objectTwo, Object objectThree) {

        if (objectone instanceof LoginUseCase && objectTwo instanceof LoginDbUseCase && objectThree instanceof LoginRemoteUseCase) {
            this.loginUseCase = (LoginUseCase) objectone;
            this.loginDbUseCase = (LoginDbUseCase) objectTwo;
            this.loginRemoteUseCase = (LoginRemoteUseCase) objectThree;

        }
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(loginUseCase, loginDbUseCase, loginRemoteUseCase);
        } else if (modelClass.isAssignableFrom(VerifyOtpViewModel.class)) {
            return (T) new VerifyOtpViewModel(loginUseCase, loginDbUseCase, loginRemoteUseCase);
        }



        throw new IllegalArgumentException("Unknown viewModel class");
    }
}
