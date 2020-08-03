package com.gid.gidassistant.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gid.gidassistant.R;
import com.gid.gidassistant.model.entities.Interest;
import com.gid.gidassistant.model.repository.RetrofitBuilder;
import com.gid.gidassistant.model.repository.interests.InterestsAPIManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private boolean isServerAnswered = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permissions_layout);
        while(!isServerAnswered) {
            serverDispatch();
        }
    }

    private void serverDispatch() {
        InterestsAPIManager interestsAPIManager = RetrofitBuilder.interestsAPIManager();
        Call<List<Interest>> list = interestsAPIManager.getAll();

        list.enqueue(new Callback<List<Interest>>() {
            @Override
            public void onResponse(Call<List<Interest>> call, Response<List<Interest>> response) {
                if(response.isSuccessful()) {
                    isServerAnswered = true;
                    Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainActivity);
                }
            }

            @Override
            public void onFailure(Call<List<Interest>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"There is no respond from server!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
