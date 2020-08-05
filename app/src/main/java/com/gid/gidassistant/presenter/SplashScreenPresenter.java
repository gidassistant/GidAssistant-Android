package com.gid.gidassistant.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;


import com.gid.gidassistant.R;
import com.gid.gidassistant.model.entities.User;
import com.gid.gidassistant.model.repository.user.UserRepository;
import com.gid.gidassistant.presenter.contracts.SplashScreenMainContract;
import com.gid.gidassistant.utils.Permissions;
import com.gid.gidassistant.view.activities.MainActivity;

import java.util.Set;
import java.util.Arrays;
import java.util.TreeSet;

public class SplashScreenPresenter implements SplashScreenMainContract.Presenter {

    private static final String TAG = "SplashScreenPresenter";

    private SplashScreenMainContract.View view;
    private SplashScreenMainContract.Model model;
    private static final int PERMISSIONS_REQUEST_CODE = 100;

    private Set<String> permissions = new TreeSet<>(Arrays.asList(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE));

    public SplashScreenPresenter(SplashScreenMainContract.View view) {
        this.view = view;
        this.model = new UserRepository();
    }

    @Override
    public void openServer() {

    }

    @Override
    public void addPermission(String... permission) {
        permissions.addAll(Arrays.asList(permission));
    }

    public void deletePermission(String... permission) {
        permissions.removeAll(Arrays.asList(permission));
    }

    @Override
    public void provideLocationPermissions(Activity activity) {
        if (Permissions.Checker.isForegroundLocationEnable(activity)) {
            ActivityCompat.requestPermissions(
                    activity,
                    new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    PERMISSIONS_REQUEST_CODE
            );
        }
    }

    public void provideSelectedPermission(Activity activity) {
        String[] permission = permissions.toArray(new String[0]);
        Log.d(TAG, "provideSelectedPermission: " + permission.length);
        if(permission.length != 0) {
            ActivityCompat.requestPermissions(
                    activity,
                    permission,
                    PERMISSIONS_REQUEST_CODE
            );
        } else {
            startMainActivity(activity);
        }
    }

    @Override
    public void provideStoragePermissions(Activity activity) {
        if (!Permissions.Checker.isReadExternalStorageEnable(activity)) {
            ActivityCompat.requestPermissions(
                    activity,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    },
                    PERMISSIONS_REQUEST_CODE
            );
        }
    }

    @Override
    public void startMainActivity(Context context) {
        context.getSharedPreferences(context.getString(R.string.project_id), Context.MODE_PRIVATE).edit().putBoolean("firstRun", false).apply();
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    public boolean isFirstRun(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(activity.getString(R.string.project_id), Context.MODE_PRIVATE);
        boolean firstRun = sharedPreferences.getBoolean("firstRun", true);
        return firstRun;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        /*switch(requestCode) {
            case PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    observer.processFinished(true, permissions);
                } else {
                    observer.processFinished(false, permissions);
                }
        }*/
    }
}
