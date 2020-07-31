package com.gid.gidassistant;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class InterestsTab extends Fragment {

    final String LOG_TAG = "myLogs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.insterests_tab, container,false);

        return v;
    }
}
