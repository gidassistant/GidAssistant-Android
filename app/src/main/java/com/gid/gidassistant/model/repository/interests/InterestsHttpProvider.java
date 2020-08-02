package com.gid.gidassistant.model.repository.interests;

import android.content.Context;
import android.widget.Toast;

import com.gid.gidassistant.R;
import com.gid.gidassistant.model.entities.Interest;
import com.gid.gidassistant.model.repository.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Класс отвечает за обращение к серверу и реализацию методов из интерфейса
 * @see com.gid.gidassistant.model.repository.interests.InterestsProvider
 *
 */
public class InterestsHttpProvider implements InterestsProvider {

    private static final int MAX_COUNT_REQUESTS = 3;

    private InterestsAPIManager interestsAPIManager;
    private Context context;

    /**
     * Главный конструктор
     * @param context Необходим для отображения диалогового окна, в случае неудачного обращения к серверу
     */
    public InterestsHttpProvider(Context context) {
        this.context = context;
    }


    @Override
    public List<Interest> getAll() {
        return getFromServer(context);
    }

    /**
     * Реализация метода получения всех инетересов.
     * @return Возвращает список интересов.
     */
    private List<Interest> getFromServer(final Context context) {
        interestsAPIManager = RetrofitBuilder.interestsAPIManager();
        final List<Interest> interests = new ArrayList<>();
        interestsAPIManager.getAll().enqueue(new Callback<List<Interest>>() {
            @Override
            public void onResponse(Call<List<Interest>> call, Response<List<Interest>> response) {
                if(response.isSuccessful() && response.body() != null) {
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
                //android.app.Dialog dialog = new Dialog.Builder(context).getServerNotRespondedDialog();
                Toast.makeText(context, "Server doesn't respond!", Toast.LENGTH_SHORT).show();
            }
        });
        return interests;
    }
}
