package com.abhishek.seller.utils;

import android.content.Context;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.abhishek.seller.R;
import com.abhishek.seller.core.application.HKApp;
import com.abhishek.seller.core.common.AppConstant;
import com.abhishek.seller.core.database.AppPref;
import com.abhishek.seller.location.model.LocationModel;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;

public class LocationUtil {

    public static LatLng getLatlng(String latitude, String longitude) {
        Double latitudeValue = ConversionUtil.INSTANCE.convertStringToDouble(latitude);
        Double longitudeValue = ConversionUtil.INSTANCE.convertStringToDouble(longitude);
        return new LatLng(latitudeValue, longitudeValue);
    }

    public static String getLocaleLanguage() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = Resources.getSystem().getConfiguration().getLocales().get(0);
        } else {
            locale = Resources.getSystem().getConfiguration().locale;
        }

        String language = locale.getLanguage();

        if (!language.equalsIgnoreCase("ar")) {
            language = "en";
        }

        return language;
    }


    public static boolean isGPSEnabled(Context mContext) {
        LocationManager locationManager = (LocationManager)
                mContext.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

    }


    public static String getAddressFromLatLng(double latitude, double longitude,
                                              boolean custom) {
        if (latitude != -1D && longitude != -1D) {
            Locale.setDefault(new Locale(AppUtil.getLocale()));
            Geocoder geocoder;
            List<Address> addresses;
            Locale.setDefault(new Locale(AppUtil.getLocale()));
            geocoder = new Geocoder(HKApp.getAppContext(), Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
                if (addresses != null && !addresses.isEmpty()) {
                    Address address = addresses.get(0);

                    if (custom) {
                        return getAddressDetail(address);
                    } else {
                        return getFullAddress(address);
                    }
                } else {
                    return HKApp.getAppContext().getString(R.string.address_not_found);
                }

            } catch (IOException e) {
                return HKApp.getAppContext().getString(R.string.address_not_found);
            }
        }
        return HKApp.getAppContext().getString(R.string.address_not_found);
    }


    public static String getCountryCode() {
        if (isLocationAvailable()) {
            LocationModel locationModel = getLocationData();
            Locale.setDefault(new Locale(AppUtil.getLocale()));
            Geocoder geocoder;
            List<Address> addresses;
            Locale.setDefault(new Locale(AppUtil.getLocale()));
            geocoder = new Geocoder(HKApp.getAppContext(), Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(ConversionUtil.INSTANCE.convertStringToDouble(locationModel.getLatitude()), ConversionUtil.INSTANCE.convertStringToDouble(locationModel.getLongitude()), 1);
                if (addresses != null && !addresses.isEmpty()) {
                    Address address = addresses.get(0);
                    return address.getCountryCode();
                }
            } catch (IOException e) {
                return "";
            }

        }
        return "";
    }


    public static String getFullAddress(Address address) {
        StringBuilder sb = new StringBuilder();
        String fullAddress = address.getAddressLine(0);
        if (fullAddress != null && !fullAddress.isEmpty()) {
            sb.append(address.getAddressLine(0));
            return sb.toString();
        } else {
            sb.append(address.getLocality()).append(",");
            sb.append(address.getPostalCode()).append(",");
            sb.append(address.getCountryName());
            if (sb.toString() != null && !sb.toString().isEmpty()) {
                return sb.toString();
            } else {
                return HKApp.getAppContext().getString(R.string.address_not_found);

            }
        }
    }


    public static String getAddressDetail(Address address) {
        StringBuilder addressCurrent = new StringBuilder();
        if (address != null && address.getSubLocality() != null && !address.getSubLocality().isEmpty()) {
            addressCurrent.append(address.getSubLocality()).toString();
        }

        if (!addressCurrent.toString().isEmpty()) {
            if (address != null && address.getLocality() != null && !address.getLocality().isEmpty()) {
                return addressCurrent.append(", " + address.getLocality()).toString();
            }
        } else {
            if (address != null && address.getLocality() != null && !address.getLocality().isEmpty()) {
                return addressCurrent.append(address.getLocality()).toString();
            } else {
                return addressCurrent.append(address.getAdminArea()).toString();

            }

        }
        return HKApp.getAppContext().getString(R.string.address_not_found);

    }

    public static void setLocationData(Location location) {
        LocationModel locationModel = new LocationModel();
        if (location != null) {
            try {
                locationModel.setLatitude("" + location.getLatitude());
                locationModel.setLongitude("" + location.getLongitude());
                locationModel.setTimestamp("" + System.currentTimeMillis());
                AppPref.INSTANCE.setStringPref(AppConstant.LOCATION_DATA, getJsonFromLocationModel(locationModel));
            } catch (Exception e) {
                Log.e("setLocationData", e.getMessage());
            }
        } else {
            AppPref.INSTANCE.setStringPref(AppConstant.LOCATION_DATA, "");
        }
    }


    public static LocationModel getLocationData() {
        if (isGPSEnabled(HKApp.getAppContext())) {
            String strJSONResponse = AppPref.INSTANCE.getPrefStringValueNotNull(AppConstant.LOCATION_DATA);
            return getLocationModelFormJson(strJSONResponse);
        }
        return null;

    }


    public static LocationModel getLocationModelFormJson(String jsondata) {
        Gson gson = new Gson();
        return gson.fromJson(jsondata, LocationModel.class);
    }

    public static String getJsonFromLocationModel(LocationModel locationModel) {
        Type locationModeltype = new TypeToken<LocationModel>() {
        }.getType();
        return new Gson().toJson(locationModel, locationModeltype);
    }


    public static boolean isLocationAvailable() {

        LocationModel locationModel = getLocationData();

        if (locationModel != null && locationModel.getLatitude() != null && !locationModel.getLatitude().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static float calculateDistance(String latitude, String longitude) {
        LocationModel locationModel = LocationUtil.getLocationData();
        if (locationModel != null && locationModel.getLatitude() != null && !locationModel.getLatitude().isEmpty()) {
            double currentlatitude = ConversionUtil.INSTANCE.convertStringToDouble(locationModel.getLatitude());
            double currentlongitude = ConversionUtil.INSTANCE.convertStringToDouble(locationModel.getLongitude());

            double outletlatitude = ConversionUtil.INSTANCE.convertStringToDouble(latitude);
            double ouletlongitude = ConversionUtil.INSTANCE.convertStringToDouble(longitude);
            float range = getDistanceBetweenCoordinates(currentlatitude, currentlongitude, outletlatitude, ouletlongitude);
            return range;
        }

        return 0;
    }

    public static float getDistanceBetweenCoordinates(double startLatitude, double startLongitude, double endLatitude, double endLongitude) {

        float[] result = new float[1];

        Location.distanceBetween(startLatitude, startLongitude, endLatitude, endLongitude, result);

        return result[0];
    }

}