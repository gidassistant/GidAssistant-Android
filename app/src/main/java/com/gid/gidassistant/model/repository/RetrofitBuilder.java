package com.gid.gidassistant.model.repository;

import com.gid.gidassistant.model.repository.interests.InterestsAPIManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Класс служит директором для строителя Retrofit.
 * В нем создается экземпляр класса по BASE_URL
 */
public class RetrofitBuilder {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://10.0.2.2:8080";

    private static Retrofit getRetrofitClient() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static InterestsAPIManager interestsAPIManager() {
        return getRetrofitClient().create(InterestsAPIManager.class);
    }
}
