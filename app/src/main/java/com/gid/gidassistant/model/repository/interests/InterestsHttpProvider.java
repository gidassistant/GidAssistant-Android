package com.gid.gidassistant.model.repository.interests;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.gid.gidassistant.R;
import com.gid.gidassistant.config.App;
import com.gid.gidassistant.model.entities.Interest;
import com.gid.gidassistant.model.repository.RetrofitBuilder;
import com.gid.gidassistant.presenter.contracts.InterestsSheetContract;
import com.gid.gidassistant.presenter.contracts.MapFragmentMainContract;
import com.google.android.gms.common.util.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Класс отвечает за обращение к серверу и реализацию методов из интерфейса
 *
 * @see MapFragmentMainContract
 */
public class InterestsHttpProvider implements MapFragmentMainContract.Repository, InterestsSheetContract.Model {

    private static final int MAX_COUNT_REQUESTS = 3;

    private InterestsAPIManager interestsAPIManager;
    private Context context;
    private MapFragmentMainContract.Presenter presenter;

    /**
     * Главный конструктор
     *
     * @param presenter Необходим для отображения диалогового окна, в случае неудачного обращения к серверу
     */
    public InterestsHttpProvider(MapFragmentMainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public InterestsHttpProvider() {

    }

    @Override
    public List<Interest> getAllInterests(Context context) {
        List<Interest> interestList = new ArrayList<>(
                Arrays.asList(
                        new Interest(1, "IT"),
                        new Interest(2, "Технологии"),
                        new Interest(3, "Кухня"),
                        new Interest(4, "Прогулки"),
                        new Interest(5, "Интересы"),
                        new Interest(6, "Кино"),
                        new Interest(7, "Кулинария")
                )
        );
        return interestList;
    }

    @Override
    public List<Interest> getByString(String s) {
        if(s.isEmpty())
            return new ArrayList<>();
        List<Interest> all = getAllInterests(App.getInstance().getApplicationContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return all.stream()
                    .filter(interest -> interest.getName().contains(s))
                    .collect(Collectors.toList());
        }
        return all;
    }

    /**
     * Реализация метода получения всех инетересов.
     *
     * @return Возвращает список интересов.
     */
    private List<Interest> getFromServer(final Context context) {
        interestsAPIManager = RetrofitBuilder.interestsAPIManager();
        final List<Interest> interests = new ArrayList<>();
        interestsAPIManager.getAll().enqueue(new Callback<List<Interest>>() {
            @Override
            public void onResponse(Call<List<Interest>> call, Response<List<Interest>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    interests.addAll(response.body());
                } else {
                    switch (response.code()) {
                        case 500:
                            Toast.makeText(context, R.string.internal_server_error, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Interest>> call, Throwable t) {

            }
        });
        return interests;
    }
}
