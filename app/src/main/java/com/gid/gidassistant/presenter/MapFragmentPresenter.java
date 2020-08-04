package com.gid.gidassistant.presenter;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.gid.gidassistant.presenter.contracts.MapFragmentMainContract;

public class MapFragmentPresenter implements MapFragmentMainContract.Presenter {

    private MapFragmentMainContract.View view;

    public MapFragmentPresenter(MapFragmentMainContract.View view) {
        this.view = view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void requestPermissions(Activity activity, int requestCode, String[] permissions) {

    }
}
