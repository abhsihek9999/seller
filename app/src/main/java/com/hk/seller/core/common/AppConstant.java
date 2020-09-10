package com.hk.seller.core.common;

public interface AppConstant {

    String PREF_OBJECT = "PREF_OBJECT";
    String PLATFORM = "ANDROID";
    String DEFAUT_COUNTRY_CODE = "+966";
    String USER_DATA = "user_primary_details";
    String AUTHORIZATION = "Authorization";
    String LOCATION_DATA = "LocationData";
    public final static int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    String FROMPASTORDER = "pastorder";
    String FROMACTIVEORDER = "activeorder";


    interface ResponseConstatnt {
        int RES_200 = 200;
        int RES_400 = 400;
        int RES_401 = 401;
        int RES_FAIL = -1;

    }

    interface PermissionCode {
        int LOCATION_PERMISSION_CODE = 783;
        int STORAGE_PERMISSION_CODE = 784;
        int READ_CONTACT_PERMISSION_CODE = 786;
        int REQUEST_CHECK_SETTINGS_GPS = 0x1;
        int CALENDER_PERMISSION_CODE = 785;

    }

    interface FragmentTransition {
        int LEFT_TO_RIGHT = 1;
        int RIGHT_TO_LEFT = 2;
        int NEITHER_LEFT_NOR_RIGHT = 3;
        int TOP_TO_DOWN = 4;
        int DOWN_TO_TOP = 5;


    }
    interface DateFormats {

        String DD_MM_YY = "dd-MM-yy";
        String DD_MM_YYYY = "dd-MM-yyyy";
        String DD_MM_YYYY_ = "dd/MM/yyyy";
        String DD_MMM_YYYY = "dd-MMM-yyyy";
        String YYYY_MM_DD = "yyyy-MM-dd";
        String DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy HH:mm:ss";
        String YYYY_MM_DD_HH_MM_AA = "yyyy-MM-dd hh:mm aa";
        String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
        String DD_MMM = "dd-MMM";
    }

    String DESCRIPTION = "description";

    interface Profile {
        String PHOTO = "photo";
        String PROFILE_MAGE_AVAIL = "PROFILE_MAGE_AVAIL";
        String PROFILE_DIRECTORY_NAME = "Viya";
        String PROFILE_IMG_NAME = "_wasl.jpg";
        String BACK_TO_PREVIOUS = "BACK_TO_PREVIOUS";
        String SMS = "SMS";
        String EMAIL = "EMAIL";
        String TEXT_PLAIN = "text/plain";
        String COM_WHATSAPP = "com.whatsapp";
        String COM_GMAIL = "com.google.android.gm";

    }
}
