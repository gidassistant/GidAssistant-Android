package com.gid.gidassistant.presenter;

import android.app.Activity;
import android.os.Build;
import android.util.Log;

import com.gid.gidassistant.R;
import com.gid.gidassistant.config.App;
import com.gid.gidassistant.model.entities.Interest;
import com.gid.gidassistant.model.repository.interests.InterestsHttpProvider;
import com.gid.gidassistant.presenter.contracts.InterestsSheetContract;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class InterestsSheetPresenter implements InterestsSheetContract.Presenter {

    private InterestsSheetContract.View view;
    private InterestsSheetContract.Model model;
    private Activity activity;
    private ChipGroup chipGroup;

    public InterestsSheetPresenter(InterestsSheetContract.View view, Activity activity, ChipGroup chipGroup) {
        this.view = view;
        this.activity = activity;
        this.chipGroup = chipGroup;
        model = new InterestsHttpProvider();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        setChips(newText);
        return false;
    }

    private static final String TAG = "InterestsSheetPresenter";

    private void setChips(String s) {
        if(s.isEmpty())
            return;
        chipGroup.removeAllViews();
        List<Interest> interestList = model.getByString(s);
        Log.d(TAG, "setChips: " + interestList.size());
        if (interestList.size() != 0) {
            for (int index = 0; index < interestList.size(); index++) {
                final Interest tagName = interestList.get(index);
                //Chip chip = new Chip(activity.getApplicationContext(), null, R.attr.CustomChipChoice);
                Chip chip =  (Chip)activity.getLayoutInflater().inflate(R.layout.single_chip_layout, chipGroup, false);

                int paddingDp = 10;
                chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
                chip.setText(tagName.getName());
                chip.setCheckable(true);
                chip.setClickable(true);


                //chip.setChipBackgroundColorResources;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    chip.setBackgroundColor(activity.getColor(R.color.secondaryDarkColor));
                }
                chip.setCheckedIconResource(R.drawable.ic_action_navigation_checked);
                //chip.setCloseIconResource(R.drawable.ic_action_navigation_close);
                //Added click listener on close icon to remove tag from ChipGroup
                chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    interestList.remove(tagName);
                });

                chipGroup.addView(chip);
            }
        }
    }
}
