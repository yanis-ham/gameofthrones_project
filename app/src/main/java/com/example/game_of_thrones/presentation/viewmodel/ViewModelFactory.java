package com.example.game_of_thrones.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.game_of_thrones.data.repository.GotCharacterDisplayRepository;

public class ViewModelFactory implements ViewModelProvider.Factory{

    private final GotCharacterDisplayRepository gotCharacterDisplayRepository;

    public ViewModelFactory(GotCharacterDisplayRepository gotCharacterDisplayRepository) {
        this.gotCharacterDisplayRepository = gotCharacterDisplayRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GotViewModel.class)) {
            return (T) new GotViewModel(gotCharacterDisplayRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
