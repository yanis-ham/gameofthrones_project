package com.example.game_of_thrones.presentation.display;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.game_of_thrones.R;
import com.example.game_of_thrones.data.di.FakeDependencyInjection;
import com.example.game_of_thrones.presentation.adapter.GotActionInterface;
import com.example.game_of_thrones.presentation.adapter.GotCharacterAdapter;
import com.example.game_of_thrones.presentation.adapter.GotCharacterViewItem;
import com.example.game_of_thrones.presentation.viewmodel.GotViewModel;

import java.util.List;

public class ListFragment extends Fragment implements GotActionInterface {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private GotCharacterAdapter gotCharacterAdapter;
    private GotViewModel charactersViewModel;
    private View gotView;


    public ListFragment(){

    }

    public static ListFragment newInstance() {
       // ListFragment fragment = new ListFragment();
        return new ListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        gotView = inflater.inflate(R.layout.fragment_list, container, false);
        return gotView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        registerViewModels();
    }

    private void setupRecyclerView() {
        recyclerView = gotView.findViewById(R.id.recycler_view);
        gotCharacterAdapter = new GotCharacterAdapter(this);
        recyclerView.setAdapter(gotCharacterAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    private void registerViewModels() {
        charactersViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(GotViewModel.class);
        charactersViewModel.getAllCharacters();
        charactersViewModel.getListCharacters().observe(getViewLifecycleOwner(), new Observer<List<GotCharacterViewItem>>() {
            @Override
            public void onChanged(List<GotCharacterViewItem> gotCharacterItemViewModelList) {
                gotCharacterAdapter.bindViewModels(gotCharacterItemViewModelList);
            }
        });

    }

    @Override
    public void onClick(int id) {
        Intent i = new Intent(getActivity(), CharacterInformationActivity.class);
        i.putExtra("Id", id);
        charactersViewModel.getCharacterById(id);
        startActivity(i);
    }
}
