package com.selincengiz.news.common

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtils {
    const val galleryPermission = Manifest.permission.READ_EXTERNAL_STORAGE


    fun Context.checkPermissionn(permission: String, onGranted: () -> Unit, onDenied: () -> Unit) {

        if (ContextCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            onGranted()
        } else {
            onDenied()
        }
    }


    fun Activity.shouldShowRationale(permission: String, onGranted: () -> Unit) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            onGranted()
        }

    }
}