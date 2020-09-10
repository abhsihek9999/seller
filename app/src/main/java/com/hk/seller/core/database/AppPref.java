package com.hk.seller.core.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hk.seller.core.application.HKApp;

import static com.hk.seller.core.common.AppConstant.PREF_OBJECT;

public enum AppPref {

    INSTANCE;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferenceEditor;
    private String PreferenceName = HKApp.getAppContext().getPackageName();
    private PrefModel prefModel;


    /**
     * GET PREF MODEL OBJECT
     * SET DATA IN MODEL
     */
    public PrefModel getModelInstance() {

        if (sharedPreferences == null) {
            sharedPreferences = HKApp.getAppContext().getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
        }

        if (getPref() != null) {
            return getPref();
        }

        if (prefModel == null) {
            prefModel = new PrefModel();
        }

        return prefModel;
    }


    /**
     * SAVE MODEL OBJECT IN SHARED PREF
     */
    public void setPref(PrefModel prefModel) {
        sharedPreferenceEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(prefModel);
        sharedPreferenceEditor.putString(PREF_OBJECT, json);
        sharedPreferenceEditor.apply(); // Thread safe // Save in background // Asynchronous
        //sharedPreferenceEditor.commit(); // Not Thread safe // Save immediately // Synchronous
    }


    private PrefModel getPref() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(PREF_OBJECT, "");
        if (json.isEmpty()) {
            return null;
        }
        return gson.fromJson(json, PrefModel.class);
    }


    public void clearPref() {

        if (sharedPreferences != null) {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

           /* PrefModel clearPrefModel = AppPref.INSTANCE.getModelInstance();
            clearPrefModel.setLogin(false);
            clearPrefModel.setUserCountry("");
            clearPrefModel.setCountryNameCode("");
            AppPref.INSTANCE.setPref(clearPrefModel);*/

        }
    }

    public void setStringPref(String key, String value) {
        sharedPreferenceEditor = HKApp.getAppContext().getSharedPreferences(PreferenceName, Context.MODE_PRIVATE).edit();
        sharedPreferenceEditor.putString(key, value).apply();
    }


    public String getPrefStringValueNotNull(String key) {

        return getStringValue(key, "");
    }

    private String getStringValue(String key, String defaultValue) {
        sharedPreferences = HKApp.getAppContext().getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }


}