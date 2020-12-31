package com.example.game_of_thrones.data.di;

import android.content.Context;

import com.example.game_of_thrones.data.api.CharacterDisplayService;
import com.example.game_of_thrones.data.repository.GotDisplayDataRepository;
import com.example.game_of_thrones.data.repository.GotDisplayRepository;
import com.example.game_of_thrones.data.repository.remote.GotDisplayRemoteDataSource;
import com.example.game_of_thrones.presentation.viewmodel.ViewModelFactory;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FakeDependencyInjection {
    private static Retrofit retrofit;
    private static Gson gson;
    private static Context applicationContext;
    private static ViewModelFactory viewModelFactory;
    private static CharacterDisplayService characterDisplayService;
    private static GotDisplayRepository characterDisplayRepository;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.thronesapi.com/api/v2/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }

    public static ViewModelFactory getViewModelFactory() {
        if (viewModelFactory == null) {
            viewModelFactory = new ViewModelFactory(getCharacterDisplayRepository());
        }
        return viewModelFactory;
    }

    public static CharacterDisplayService getCharacterDisplayService() {
        if (characterDisplayService == null) {
            characterDisplayService = getRetrofit().create(CharacterDisplayService.class);
        }
        return characterDisplayService;
    }

    public static GotDisplayRepository getCharacterDisplayRepository() {
        if (characterDisplayRepository == null) {
            characterDisplayRepository = new GotDisplayDataRepository(
                    new GotDisplayRemoteDataSource(getCharacterDisplayService())
            );
        }
        return characterDisplayRepository;
    }

}
