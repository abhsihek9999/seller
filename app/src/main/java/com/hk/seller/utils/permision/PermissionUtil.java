package com.hk.seller.utils.permision;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;

import com.hk.seller.R;
import com.hk.seller.core.common.Status;
import com.hk.seller.utils.LocationUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;
import static com.hk.seller.core.common.AppConstant.PermissionCode.LOCATION_PERMISSION_CODE;
import static com.hk.seller.core.common.AppConstant.PermissionCode.STORAGE_PERMISSION_CODE;

public class PermissionUtil {
    private static final String TAG = "PermissionUtils";
    private static AlertDialog alert;

    // List of callbacks in case instance is used for multiple permission checks. Uses Dictionary to permit removing reference once complete.
    @SuppressLint("UseSparseArrays")
    private Map<Integer, PermissionsCallback> mCallbacks = new HashMap<>();
    private static final String[] allPermissions = new String[]{CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION};
    private static final String[] mCameraPermissions = new String[]{CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE};
    private static final String[] mLocationPermission = new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION};
    private static final String[] mStorage = new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE};
    private static final String[] mStorageAndLocation = new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION};

    private void checkPermissions(Activity activity, PermissionsCallback callback, String[] permissions, boolean requestPermissions, int requestcode) {

        if (activity != null) {

            Context ctx = activity.getApplicationContext();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                success(callback, false);
            }
            ArrayList<String> missingPermissions = new ArrayList<>();
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(ctx, permission) != PERMISSION_GRANTED) {
                    missingPermissions.add(permission);
                }
            }

            if (missingPermissions.isEmpty()) {
                Log.d(TAG, "Permission(s) already granted");
                success(callback, false);
            } else {
                Log.d(TAG, "Permission(s) not yet granted.");

                if (requestPermissions) {
                    Log.d(TAG, "Requesting needed permissions.");
                    mCallbacks.put(requestcode, callback);
                    ActivityCompat.requestPermissions(activity, missingPermissions.toArray(new String[missingPermissions.size()]), requestcode);

                    requestPermision(callback);
                } else {
                    Log.d(TAG, "Permissions not being requested.");
                    denied(callback);
                }
            }
        }
    }

    private void requestPermision(PermissionsCallback callback) {
        callback.onPermisionRequest();
    }

    public static boolean checkStoragePermission(Context ctx) {
        return verifyPermissions(ctx, mStorage);
    }


    public static boolean checkLocationPermission(Context ctx) {
        return verifyPermissions(ctx, mLocationPermission);
    }

    private static boolean verifyPermissions(Context ctx, String[] permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(ctx, permission) != PERMISSION_GRANTED) {
                Log.d(TAG, "Missing permission(s) in verifyPermissions: " + permission);
                return false;
            }
        }
        Log.d(TAG, "Permissions were already granted");
        return true;
    }


    public void checkAllAppPermissions(Activity activity, PermissionsCallback callback, int requestcode) {
        checkPermissions(activity, callback, allPermissions, true, requestcode);
    }


    public static boolean verifyAllPermissions(Context ctx) {
        return verifyPermissions(ctx, allPermissions);
    }

    public void checkCameraPermissions(Activity activity, PermissionsCallback callback, boolean requestPermissions, int requestcode) {
        checkPermissions(activity, callback, mCameraPermissions, requestPermissions, requestcode);
    }

    public static boolean verifyCameraPermissions(Context ctx) {
        return verifyPermissions(ctx, mCameraPermissions);
    }


    public static boolean verifyMicPermissions(Context ctx) {
        return verifyPermissions(ctx, new String[]{RECORD_AUDIO});
    }

    public void checkStoragePermission(Activity activity, PermissionsCallback callback, boolean requestPermissions, int requestcode) {
        checkPermissions(activity, callback, mStorage, requestPermissions, requestcode);
    }

    public void checkStorageAndLocationPermission(Activity activity, PermissionsCallback callback, boolean requestPermissions, int requestcode) {
        checkPermissions(activity, callback, mStorageAndLocation, requestPermissions, requestcode);
    }

    public static boolean verifyStoragePermissions(Context ctx) {
        return verifyPermissions(ctx, mStorage);
    }

    public void checkLocationPermissions(Activity activity, PermissionsCallback callback, boolean requestPermissions, int requestcode) {
        checkPermissions(activity, callback, mLocationPermission, requestPermissions, requestcode);
    }


    public static boolean verifyLocationPermissions(Context ctx) {
        return verifyPermissions(ctx, mLocationPermission);
    }

    // Checks results of permissions call. All requested permissions must be granted for a successful result
    public void onRequestPermissionsResult(Activity activity, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, PermissionListener permissionListener) {
        PermissionsCallback callback = mCallbacks.get(requestCode);
        mCallbacks.remove(requestCode);
        Log.d(TAG, "Permissions resolved for request #" + requestCode + ": " + grantResults.length);
        for (int i = 0; i < grantResults.length; i++) {
            boolean granted = grantResults[i] == PERMISSION_GRANTED;
            Log.d(TAG, "Permission resolved: " + permissions[i] + "(" + grantResults[i] + " = " + (granted ? "GRANTED" : "NOT GRANTED") + ")");
            if (!granted) {
                LocationUtil.setLocationData(null);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!activity.shouldShowRequestPermissionRationale(permissions[i])) {
                        if (requestCode == LOCATION_PERMISSION_CODE) {
                            showAlertMessageInCasePermissionNotGranted(activity.getString(R.string.location), activity.getString(R.string.location), activity, permissionListener, requestCode);
                        } else if (requestCode == STORAGE_PERMISSION_CODE) {
                            showAlertMessageInCasePermissionNotGranted(activity.getString(R.string.storage), activity.getString(R.string.storage), activity, permissionListener, requestCode);
                        }
                        return;
                    } else {
                        if (permissionListener != null) {
                            permissionListener.permissionCallback(requestCode, Status.NORMAL_DENIED);
                        }
                        Log.d(TAG, "Permission simply denied");
                    }
                }
                denied(callback);
                return;
            }
        }
        Log.d(TAG, "All requested permissions granted for request #" + requestCode);
        success(callback, true);
    }

    private void success(PermissionsCallback callback, boolean newPermissionsGranted) {
        if (callback != null) {
            callback.onGranted(newPermissionsGranted);
        }
    }

    private void denied(PermissionsCallback callback) {
        if (callback != null) {
            callback.onDenied();
        }
    }


    public static void showAlertMessageInCasePermissionNotGranted(String title, String pName, final Context context, PermissionListener permissionListener, int requestCode) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(context.getString(R.string.app_name));
            builder.setMessage(context.getString(R.string.to_take) + " " + title + " " + context.getString(R.string.app_name) + " " + pName + " " + context.getString(R.string.tap_select) + " " + pName + " on.").setCancelable(false);
            builder.setNegativeButton(context.getString(R.string.not_now), (dialog, which) -> {
                if (permissionListener != null) {
                    permissionListener.permissionCallback(requestCode, Status.PERMANENT_DENIED);
                }

                dialog.cancel();
            });

            builder.setPositiveButton(context.getString(R.string.settings), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                    intent.setData(uri);
                    ((AppCompatActivity) context).startActivityForResult(intent, requestCode);
                }
            });


            if (alert == null) {
                alert = builder.create();
                alert.show();
            } else {
                if (!alert.isShowing()) {
                    alert = builder.create();
                    alert.show();
                }
            }
            Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
            nbutton.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
            pbutton.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        } catch (Exception e) {
            // Nothing to do
        }
    }
}