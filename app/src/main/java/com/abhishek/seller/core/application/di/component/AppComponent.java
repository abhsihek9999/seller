package com.abhishek.seller.core.application.di.component;


import com.abhishek.seller.core.application.HKApp;
import com.abhishek.seller.core.application.di.module.AppModule;
import com.abhishek.seller.core.application.di.module.LoginModule;
import com.abhishek.seller.core.application.di.module.UtilsModule;
import com.abhishek.seller.login.presentation.view.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, LoginModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {
    void injectAppContext(HKApp hkApp);

    void doInjection(LoginActivity loginActivity);

}
