package com.gid.gidassistant.presenter.contracts;

import android.content.Context;

import androidx.appcompat.widget.SearchView;

import com.gid.gidassistant.model.entities.Interest;

import java.util.List;

public interface InterestsSheetContract {

    interface Model {
        List<Interest> getAllInterests(Context context);
        List<Interest> getByString(String s);
    }

    interface View {

    }

    interface Presenter extends SearchView.OnQueryTextListener {

    }
}
