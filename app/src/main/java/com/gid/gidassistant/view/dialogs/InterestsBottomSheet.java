package com.gid.gidassistant.view.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gid.gidassistant.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class InterestsBottomSheet extends BottomSheetDialogFragment {

    private Context context;
    private View thisView;

    public InterestsBottomSheet(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        thisView = inflater.inflate(R.layout.insterests_tab, container, false);
        return thisView;
    }
}
