package com.abhishek.seller.utils.permision;


import com.abhishek.seller.core.common.Status;

public interface PermissionListener {

    void permissionCallback(int requestCode, Status status);
}