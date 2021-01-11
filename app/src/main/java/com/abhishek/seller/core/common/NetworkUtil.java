package com.abhishek.seller.core.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.abhishek.seller.core.application.HKApp;


import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NetworkUtil {

    public static Single<Boolean> hasInternetConnection() {

        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                final ConnectivityManager connMgr = (ConnectivityManager) HKApp.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                if (connMgr != null) {
                    NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

                    if (activeNetworkInfo != null) { // connected to the internet
                        // connected to the mobile provider's data plan
                        if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                            // connected to wifi
                            return true;
                        } else
                            return activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
                    }
                }
                return false;
               /* Socket socket = new Socket();
                try {
                    int timeoutMs = 6000;
                    SocketAddress socketAddress = new InetSocketAddress("8.8.8.8", 53);
                    socket.connect(socketAddress, timeoutMs);
                    return true;
                } catch (Exception e) {
                    socket.close();
                    return false;
                } finally {
                    socket.close();
                }*/
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

 /*   public static Single<Boolean> hasOtpInternetConnection() {

        Single<Boolean> tSingle = new SingleOnSubscribe<Boolean>() {

            @Override
            public void subscribe(SingleEmitter<Boolean> emitter) throws Exception {

                Socket socket = new Socket();
                try {
                    int timeoutMs = 1500;
                    SocketAddress socketAddress = new InetSocketAddress("8.8.8.8", 53);
                    socket.connect(socketAddress, timeoutMs);
                    return true;
                } catch (Exception e) {
                    socket.close();
                    return false;
                } finally {
                    socket.close();
                }

            }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return tSingle;
    }
*/

    public static boolean hasInternet(Context context) {
        boolean flag = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
                flag = true;
            }
        } catch (Exception e) {
        }

        return flag;
    }

}
