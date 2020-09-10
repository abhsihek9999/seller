package com.hk.seller.utils.permision;


import com.hk.seller.core.common.Status;

public interface PermissionListener {

    void permissionCallback(int requestCode, Status status);
}