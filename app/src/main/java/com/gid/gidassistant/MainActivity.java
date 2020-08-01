package com.gid.gidassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment mapTab = new MapFragment(this, this);
        getSupportFragmentManager()
                .beginTransaction()
                //.add(R.id.interests_tab_frag, interestsTab)
                .add(R.id.map_fragment, mapTab)
                .commit();
    }
}