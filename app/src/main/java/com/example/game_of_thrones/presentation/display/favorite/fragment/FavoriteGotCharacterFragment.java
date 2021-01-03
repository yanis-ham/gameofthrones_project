package com.example.game_of_thrones.presentation.display.favorite.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.game_of_thrones.R;
import com.example.game_of_thrones.data.di.FakeDependencyInjection;
import com.example.game_of_thrones.presentation.display.favorite.adapter.FavoriteGotCharacterActionInterface;
import com.example.game_of_thrones.presentation.display.favorite.adapter.FavoriteGotCharacterAdapter;
import com.example.game_of_thrones.presentation.viewmodel.FavoriteGotCharacterViewModel;

public class FavoriteGotCharacterFragment extends Fragment implements FavoriteGotCharacterActionInterface {

    private FavoriteGotCharacterViewModel favoriteGotCharacterViewModel;
    private FavoriteGotCharacterAdapter favoriteGotCharacterAdapter;
    private View favoriteView;
    private RecyclerView recyclerView;

    private FavoriteGotCharacterFragment() {

    }

    public static FavoriteGotCharacterFragment newInstance() {
        return new FavoriteGotCharacterFragment();
    }

    @Override
    public void onRemove(int id) {
        favoriteGotCharacterViewModel.deleteCharacterFromFavorites(id);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        favoriteView = inflater.inflate(R.layout.fragment_favorite_got_character, container, false);
        return favoriteView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        registerViewModels();
    }

    private void setupRecyclerView() {
        recyclerView = favoriteView.findViewById(R.id.recycler_view);
        favoriteGotCharacterAdapter = new FavoriteGotCharacterAdapter(this);
        recyclerView.setAdapter(favoriteGotCharacterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void registerViewModels() {
        favoriteGotCharacterViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(FavoriteGotCharacterViewModel.class);
        favoriteGotCharacterViewModel.getFavoriteCharacters();
        favoriteGotCharacterViewModel.getGotCharacters().observe(getViewLifecycleOwner(), characterItemViewModelList -> favoriteGotCharacterAdapter.bindViewModels(characterItemViewModelList));

        favoriteGotCharacterViewModel.getCharacterAddedEvent().observe(getViewLifecycleOwner(), stringEvent -> {

        });
        favoriteGotCharacterViewModel.getCharacterDeletedEvent().observe(getViewLifecycleOwner(), stringEvent -> {

        });
    }
}
