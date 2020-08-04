package com.gid.gidassistant.model.repository.interests;

import com.gid.gidassistant.model.entities.Interest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Интерфейс взаимодействия с сервером, для получения списка интересов.
 */
public interface InterestsAPIManager {
    @GET("/interests/getAll")
    Call<List<Interest>> getAll();
}
