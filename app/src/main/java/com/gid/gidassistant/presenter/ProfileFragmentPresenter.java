package com.gid.gidassistant.presenter;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;

import com.gid.gidassistant.presenter.contracts.ProfileFragmentMainContract;

public class ProfileFragmentPresenter implements ProfileFragmentMainContract.Presenter{

    private ProfileFragmentMainContract.View view;

    public ProfileFragmentPresenter(ProfileFragmentMainContract.View view) {
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
