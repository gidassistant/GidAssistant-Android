package com.gid.gidassistant.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permissions extends Activity {

    private static final String[] mainPermissions = new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private static final int PERMISSIONS_REQUEST_CODE = 100;


    private Observer observer;

    private Permissions(@NonNull Observer observer) {
        this.observer = observer;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    observer.processFinished(true);
                } else {
                    observer.processFinished(false);
                }
        }
    }

    private interface Observer {
        void processFinished(boolean isSuccess);
    }

    public static class Checker {

        public static boolean isForegroundLocationEnable(Context context){
            return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        }

        public static boolean isBackgroundLocationEnable(Context context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED;
            }
            return false;
        }

        public static boolean isWriteExternalStorageEnable(Context context) {
            return ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }

        public static boolean isReadExternalStorageEnable(Context context) {
            return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
    }

    public static class Provider implements Observer {

        private PermissionsObserver observer;

        public Provider(@NonNull PermissionsObserver observer) {
            this.observer = observer;
        }

        public void requestPermissions(Activity activity, String[] permissions, int requestCode) {
            PackageManager packageManager = activity.getPackageManager();
            String packageName = activity.getPackageName();
        }

        public void requestForegroundLocationPermission(Activity activity) {
            Intent intent = new Intent(activity, Permissions.class);
            activity.startActivity(intent);
            Permissions permission = new Permissions(Provider.this);
            ActivityCompat.requestPermissions(
                    permission,
                    new String[] { Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION },
                    PERMISSIONS_REQUEST_CODE
            );
        }

        public void requestWriteExternalStoragePermission() {
            Permissions permission = new Permissions(Provider.this);
            ActivityCompat.requestPermissions(
                    permission,
                    new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
                    PERMISSIONS_REQUEST_CODE
            );
        }

        public void requestBackgroundLocation() {
            Permissions permission = new Permissions(Provider.this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ActivityCompat.requestPermissions(
                        permission,
                        new String[] { Manifest.permission.ACCESS_BACKGROUND_LOCATION },
                        PERMISSIONS_REQUEST_CODE
                );
            }
        }

        public void requestNotificationPolicyPermission() {
            Permissions permission = new Permissions(Provider.this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(
                        permission,
                        new String[] { Manifest.permission.ACCESS_NOTIFICATION_POLICY },
                        PERMISSIONS_REQUEST_CODE
                );
            }
        }

        public void requestCallPhonePermission() {
            Permissions permission = new Permissions(Provider.this);
            ActivityCompat.requestPermissions(
                    permission,
                    new String[] { Manifest.permission.CALL_PHONE },
                    PERMISSIONS_REQUEST_CODE
            );
        }

        public void requestReadContactsPermission() {
            Permissions permission = new Permissions(Provider.this);
            ActivityCompat.requestPermissions(
                    permission,
                    new String[] { Manifest.permission.READ_CONTACTS },
                    PERMISSIONS_REQUEST_CODE
            );
        }

        @Override
        public void processFinished(boolean isSuccess) {
            if(isSuccess){
                observer.onRequestPermissionGranted();
            } else{
                observer.onRequestPermissionDenied();
            }
        }
    }

    public interface PermissionsObserver {
        void onRequestPermissionGranted();
        void onRequestPermissionDenied();
    }
}
