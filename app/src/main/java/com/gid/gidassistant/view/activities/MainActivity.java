package com.gid.gidassistant.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;

import com.gid.gidassistant.view.fragments.MapFragment;
import com.gid.gidassistant.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment mapTab = new MapFragment(this, this);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.map_fragment, mapTab)
                .commit();
    }
}