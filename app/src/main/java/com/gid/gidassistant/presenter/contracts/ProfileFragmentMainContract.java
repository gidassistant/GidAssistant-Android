package com.gid.gidassistant.presenter.contracts;

import android.app.Activity;

import androidx.core.app.ActivityCompat;

public interface ProfileFragmentMainContract {

    interface View {

    }

    interface Presenter extends ActivityCompat.OnRequestPermissionsResultCallback{
        void onCreate();
        void onDestroy();
        void requestPermissions(Activity activity, int requestCode, String[] permissions);
    }

    interface Repository {

    }
}
