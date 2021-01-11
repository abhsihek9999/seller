
package com.abhishek.seller.utils.permision;

@SuppressWarnings("WeakerAccess")
public abstract class PermissionsCallback {
    public void onGranted(boolean newPermissionsGranted) {
    }

    public void onDenied() {
    }

    public void onPermisionRequest() {

    }
}