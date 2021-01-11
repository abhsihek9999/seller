package com.abhishek.seller.core.application.di.module;

import com.google.gson.Gson;
import com.abhishek.seller.core.application.di.ViewModelFactory;
import com.abhishek.seller.login.data.datasource.database.LoginDbDataSourceimpl;
import com.abhishek.seller.login.data.datasource.remote.LoginRemoteApi;
import com.abhishek.seller.login.data.datasource.remote.LoginRemoteDataSourceImpl;
import com.abhishek.seller.login.data.repository.LoginRepo;
import com.abhishek.seller.login.domain.LoginDbUseCase;
import com.abhishek.seller.login.domain.LoginRemoteUseCase;
import com.abhishek.seller.login.domain.LoginUseCase;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class LoginModule {

    @Provides
    @Singleton
    LoginRemoteApi providesLoginApi(Retrofit retrofit) {

        return retrofit.create(LoginRemoteApi.class);

    }

    @Provides
    @Singleton
    LoginRepo.LoginDbInteractor provideBasketDbDataSourceImp() {
        return new LoginDbDataSourceimpl();
    }

    @Provides
    @Singleton
    LoginRepo.LoginRemoteInteractor provideBasketRemoteDataSourceImp(LoginRemoteApi loginRemoteApi) {
        return new LoginRemoteDataSourceImpl(loginRemoteApi);
    }

    @Provides
    @Singleton
    LoginUseCase providesLoginUseCase() {
        return new LoginUseCase();
    }

    @Provides
    @Singleton
    LoginDbUseCase providesDataLoginUseCase(LoginRepo.LoginDbInteractor loginDbInteractor) {
        return new LoginDbUseCase(loginDbInteractor);
    }

    @Provides
    @Singleton
    LoginRemoteUseCase providesRemoteLoginUseCase(LoginRepo.LoginRemoteInteractor loginRemoteInteractor, Gson gson) {
        return new LoginRemoteUseCase(loginRemoteInteractor, gson);
    }


    @Provides
    @Singleton
    @Named("LoginActivity")
    ViewModelFactory provideLoginViewModelFactory(LoginUseCase loginUseCase, LoginDbUseCase loginDbUseCase, LoginRemoteUseCase loginRemoteUseCase) {
        return new ViewModelFactory(loginUseCase, loginDbUseCase, loginRemoteUseCase);
    }



}
