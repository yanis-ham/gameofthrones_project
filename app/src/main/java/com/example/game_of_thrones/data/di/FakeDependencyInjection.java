package com.example.game_of_thrones.data.di;

import android.content.Context;

import androidx.room.Room;

import com.example.game_of_thrones.data.api.GotCharacterDisplayService;
import com.example.game_of_thrones.data.api.model.GotCharacter;
import com.example.game_of_thrones.data.db.GotCharacterDatabase;
import com.example.game_of_thrones.data.repository.GotCharacterDisplayDataRepository;
import com.example.game_of_thrones.data.repository.GotCharacterDisplayRepository;
import com.example.game_of_thrones.data.repository.local.GotCharacterDisplayLocalDataSource;
import com.example.game_of_thrones.data.repository.remote.GotCharacterDisplayRemoteDataSource;
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
    private static GotCharacterDisplayService gotCharacterDisplayService;
    private static GotCharacterDisplayRepository characterDisplayRepository;
    private static GotCharacterDatabase gotCharacterDatabase;

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

    public static GotCharacterDisplayService getGotCharacterDisplayService() {
        if (gotCharacterDisplayService == null) {
            gotCharacterDisplayService = getRetrofit().create(GotCharacterDisplayService.class);
        }
        return gotCharacterDisplayService;
    }

    public static GotCharacterDisplayRepository getCharacterDisplayRepository() {
        if (characterDisplayRepository == null) {
            characterDisplayRepository = new GotCharacterDisplayDataRepository(
                    new GotCharacterDisplayRemoteDataSource(getGotCharacterDisplayService()),
                    new GotCharacterDisplayLocalDataSource(getGotCharacterDatabase())
            );
        }
        return characterDisplayRepository;
    }

    public static GotCharacterDatabase getGotCharacterDatabase() {
        if (gotCharacterDatabase == null) {
            gotCharacterDatabase = Room.databaseBuilder(applicationContext,
                    GotCharacterDatabase.class, "gotCharacter-database").build();
        }
        return gotCharacterDatabase;
    }

}
