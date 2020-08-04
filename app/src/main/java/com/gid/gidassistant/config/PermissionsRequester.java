package com.gid.gidassistant.config;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class PermissionsRequester implements EasyPermissions.PermissionCallbacks {

    private Context context;
    private Activity activity;

    public PermissionsRequester(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @AfterPermissionGranted(123)
    public boolean providePermissions() {
        String[] perms = {Manifest.permission_group.LOCATION,
                        Manifest.permission_group.STORAGE};

        if (EasyPermissions.hasPermissions(context, perms)) {
            Toast.makeText(context, "Permissions granted", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            EasyPermissions.requestPermissions(activity, "We need permissions because this and that",
                    123, perms);
            return false;
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }
}
