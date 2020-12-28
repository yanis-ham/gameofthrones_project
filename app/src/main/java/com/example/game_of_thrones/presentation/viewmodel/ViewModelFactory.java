package com.example.game_of_thrones.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.game_of_thrones.data.repository.GotDisplayRepository;

public class ViewModelFactory implements ViewModelProvider.Factory{

    private final GotDisplayRepository gotDisplayRepository;

    public ViewModelFactory(GotDisplayRepository gotDisplayRepository) {
        this.gotDisplayRepository = gotDisplayRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GotViewModel.class)) {
            return (T) new GotViewModel(gotDisplayRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
