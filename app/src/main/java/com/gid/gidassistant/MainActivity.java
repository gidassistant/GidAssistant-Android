package com.gid.gidassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.gid.gidassistant.dialogs.InterestsBottomDialog;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MainActivity extends AppCompatActivity {

    Button interestsButton;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //checkPermission();

        Fragment interestsTab = new InterestsTab();
        Fragment mapTab = new MapFragment(this, this);
        getSupportFragmentManager()
                .beginTransaction()
                //.add(R.id.interests_tab_frag, interestsTab)
                .add(R.id.map_fragment, mapTab)
                .commit();
    }
}
