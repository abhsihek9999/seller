package com.abhishek.seller.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.abhishek.seller.BuildConfig;
import com.abhishek.seller.core.application.HKApp;
import com.abhishek.seller.core.common.AppConstant;
import com.abhishek.seller.core.common.CommonDataModel;
import com.abhishek.seller.core.common.Status;
import com.abhishek.seller.core.database.AppPref;
import com.abhishek.seller.core.database.PrefModel;


import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;

import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import static com.abhishek.seller.core.common.AppConstant.Profile.PROFILE_DIRECTORY_NAME;
import static com.abhishek.seller.core.common.AppConstant.Profile.PROFILE_IMG_NAME;

public class AppUtil {


    public static String getVersionNumber() {
        String lStrVersion = "";
        try {
            PackageInfo pInfo = HKApp.getAppContext().getPackageManager().getPackageInfo(HKApp.getAppContext().getPackageName(), 0);
            lStrVersion = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return lStrVersion;
    }


    public static String loadData(String inFile, Context context) {
        String tContents = "";

        try {
            InputStream stream = context.getAssets().open(inFile);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }

        return tContents;

    }


    public static String getLocale() {
        return Locale.getDefault().getLanguage().toUpperCase();
    }

    /*  public static CommonDataModel getCommonClickModel(int source, Status clickType, Object object) {
          CommonDataModel commonDataModel = new CommonDataModel();
          commonDataModel.setSource(source);
          commonDataModel.setClickType(clickType);
          commonDataModel.setObject(object);
          return commonDataModel;
      }
  */
    public static float dpToPx(@NonNull Context context, @Dimension(unit = Dimension.DP) int dp) {
        Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }


    public static String errorMessageHandler(String defaultMsg, String responseData) {

        JSONObject errorJson = null;
        try {
            errorJson = new JSONObject(responseData);
            JSONObject description = (JSONObject) errorJson.getJSONArray("errorDetails").opt(0);
            return description.optString(AppConstant.DESCRIPTION);

        } catch (Exception e) {
            return defaultMsg;
        }
    }

    public static void openDialer(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));
        context.startActivity(intent);
    }

    public static void openUrlInBrowser(String url, Context context) {
        try {
            Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        } catch (Exception e) {

        }
    }

    public static String saveImage(Bitmap image, Context context) {
        String savedImagePath = null;

        File storageDir = new File(Environment.getExternalStorageDirectory(), PROFILE_DIRECTORY_NAME);
        boolean success = true;
        if (!storageDir.exists()) {
            success = storageDir.mkdirs();
        }
        if (success) {
            File imageFile = new File(storageDir, PROFILE_IMG_NAME);
            savedImagePath = imageFile.getAbsolutePath();
            try {
                OutputStream fOut = new FileOutputStream(imageFile);
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                fOut.close();

                PrefModel model = AppPref.INSTANCE.getModelInstance();
                /*remove comment later*/
                //  model.setProfilePathLocal("file://" + savedImagePath);
                AppPref.INSTANCE.setPref(model);

            } catch (Exception e) {
            }

            // Add the image to the system gallery
            galleryAddPic(savedImagePath, context);
        }
        return savedImagePath;
    }

    private static void galleryAddPic(String imagePath, Context context) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(imagePath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }

    public static void closePip(Activity activity) {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("com.package.ACTION_KILL");//some string
        LocalBroadcastManager.getInstance(activity).sendBroadcast(broadcastIntent);
    }


    public static boolean isApplicationSentToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (TextUtil.checkListIsEmpty(tasks)) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equalsIgnoreCase(context.getPackageName())) {
                return true;
            }
        }

        return false;
    }


    public static int getWindowHeight(Context context) {
        // Calculate window height for fullscreen use
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static void openGooglePlayStore(Context context) {
        final String appPackageName = BuildConfig.APPLICATION_ID; // getPackageName() from Context or Activity object
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public static CommonDataModel getCommonClickModel(int source, Status clickType, Object object) {
        CommonDataModel commonDataModel = new CommonDataModel();
        commonDataModel.setSource(source);
        commonDataModel.setClickType(clickType);
        commonDataModel.setObject(object);
        return commonDataModel;
    }

    public static String AndroidId() {
        String ANDROIDID = "ANDROIDKEY";
        if (Settings.Secure.getString(HKApp.getAppContext().getContentResolver(), Settings.Secure.ANDROID_ID).isEmpty()) {
            return ANDROIDID;
        } else {
            ANDROIDID = Settings.Secure.getString(HKApp.getAppContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        }


        return ANDROIDID;
    }
}
