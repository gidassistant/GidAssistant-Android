package com.gid.gidassistant.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gid.gidassistant.R;
import com.gid.gidassistant.view.adapters.best.BestItemAdapter;
import com.gid.gidassistant.view.adapters.best.SpacesItemDecoration;

public class BestFragment extends Fragment {



    String mTitle[] = {"Facebook", "Whatsapp", "Twitter", "Instagram", "Youtube"};
    String mHeading[] = {"Facebook", "Whatsapp", "Twitter", "Instagram", "Youtube"};
    String mDescription[] = {"Facebook Description", "Whatsapp Description", "Twitter Description", "Instagram Description", "Youtube Description"};
    int images[] = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};

    private View view;
    private RecyclerView listItem;
    // private ProgressBar progressBar;

    public BestFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.best_layout, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.rv_best_items);

        BestItemAdapter bestItemAdapter = new BestItemAdapter(getActivity(), mTitle, mHeading, mDescription, images);

        recyclerView.setAdapter(bestItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new SpacesItemDecoration(100));

        return view;
    }


}
