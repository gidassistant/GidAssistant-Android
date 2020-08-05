package com.gid.gidassistant.view.activities;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.gid.gidassistant.R;
import com.gid.gidassistant.presenter.SplashScreenPresenter;
import com.gid.gidassistant.presenter.contracts.SplashScreenMainContract;

public class SplashActivity extends AppCompatActivity implements SplashScreenMainContract.View {

    private static final String TAG = "SplashActivity";

    private boolean isServerAnswered = false;
    private TextView textView;
    private SplashScreenMainContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permissions_layout);
        presenter = new SplashScreenPresenter(this);
        if(!presenter.isFirstRun(this)){
            Log.d(TAG, "onCreate: " + "not first run");
            presenter.startMainActivity(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setActiveText();
    }

    private void setActiveText() {
        textView = findViewById(R.id.lorem_ipsum);

        //  textView.setHighlightColor(Color.TRANSPARENT);
        Spannable spannableString = new SpannableString(textView.getText());
        //    new SpannableString("more information no action random text ");

        ClickableSpan linkClick = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                openLicence(view);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                if (textView.isPressed()) {
                    ds.setColor(Color.parseColor("#2E2E2E"));
                } else {
                    ds.setColor(Color.parseColor("#4385F5"));
                }
                textView.invalidate();
            }
        };

        String needWord = "more information";
        String text = textView.getText().toString();

        int f = text.lastIndexOf(needWord);
        int first = f + needWord.length();

        spannableString.setSpan(linkClick, f, first, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

    }

    @Override
    public void openPrivacyPolice(View view) {
        Toast.makeText(getApplicationContext(), "more information", Toast.LENGTH_SHORT).show();
        view.invalidate();
    }

    @Override
    public void openLicence(View view) {

    }

    @Override
    public void onLocationSwitched(View view) {
        SwitchCompat switchCompat = (SwitchCompat)view;
        Log.d(TAG, "onLocationSwitched: " + switchCompat.isChecked());
        if(switchCompat.isChecked()) {
            presenter.addPermission(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
        } else {
            presenter.deletePermission(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
        }
    }

    @Override
    public void onStorageSwitched(View view) {
        SwitchCompat switchCompat = (SwitchCompat)view;
        Log.d(TAG, "onLocationSwitched: " + switchCompat.isChecked());
        if(switchCompat.isChecked()) {
            presenter.addPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        } else {
            presenter.deletePermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onNextButtonClick(View view) {
        presenter.provideSelectedPermission(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.startMainActivity(this);
    }
}
