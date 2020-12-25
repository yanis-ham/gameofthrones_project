package com.example.game_of_thrones;

import android.app.Application;

import com.example.game_of_thrones.data.di.FakeDependencyInjection;

public class GotApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FakeDependencyInjection.setContext(this);
    }

}