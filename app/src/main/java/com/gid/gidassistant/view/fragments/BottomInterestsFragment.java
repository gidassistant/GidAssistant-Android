package com.gid.gidassistant.view.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;

import com.gid.gidassistant.R;
import com.gid.gidassistant.presenter.InterestsSheetPresenter;
import com.gid.gidassistant.presenter.contracts.InterestsSheetContract;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomInterestsFragment extends BottomSheetDialogFragment implements InterestsSheetContract.View {

    private InterestsSheetContract.Presenter presenter;
    private View view;
    private LinearLayout linearLayout;

    private BottomInterestsFragment() {

    }

    public static BottomInterestsFragment newInstance() {
        return new BottomInterestsFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.insterests_tab, null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        presenter = new InterestsSheetPresenter(this, getActivity(), dialog.findViewById(R.id.interests_chipGroup));
        SearchView searchView = dialog.findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(presenter);

    }
}
