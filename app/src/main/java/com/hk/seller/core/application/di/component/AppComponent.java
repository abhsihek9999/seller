package com.hk.seller.core.application.di.component;


import com.hk.seller.core.application.HKApp;
import com.hk.seller.core.application.di.module.AppModule;
import com.hk.seller.core.application.di.module.LoginModule;
import com.hk.seller.core.application.di.module.UtilsModule;
import com.hk.seller.login.presentation.view.LoginActivity;
import com.hk.seller.login.presentation.view.VerifyOtpActivity;
import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, LoginModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {
    void injectAppContext(HKApp hkApp);

    void doInjection(LoginActivity loginActivity);

    void doInjection(VerifyOtpActivity verifyOtpActivity);

}
