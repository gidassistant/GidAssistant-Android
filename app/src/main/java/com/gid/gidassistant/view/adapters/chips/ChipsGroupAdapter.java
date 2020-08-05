package com.gid.gidassistant.view.adapters.chips;

import android.content.Context;

import com.gid.gidassistant.model.entities.Interest;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class ChipsGroupAdapter extends ChipGroup {

    private Context context;
    private List<Interest> interestList;

    public ChipsGroupAdapter(Context context, List<Interest> interestList) {
        super(context);
        this.context = context;
        this.interestList = interestList;
    }
}
