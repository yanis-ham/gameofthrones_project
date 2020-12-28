package com.example.game_of_thrones.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.game_of_thrones.data.api.model.CharacterInformation;
import com.example.game_of_thrones.data.repository.GotDisplayRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class GotViewModel extends ViewModel{
    private GotDisplayRepository gotDisplayRepository;
    private CompositeDisposable compositeDisposable;

    public GotViewModel(GotDisplayRepository gotDisplayRepository){
        this.gotDisplayRepository = gotDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    public void getCharacterById(int id){
        compositeDisposable.clear();
        compositeDisposable.add(gotDisplayRepository.getCharacterById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<CharacterInformation>>(){

                    @Override
                    public void onSuccess(@NonNull List<CharacterInformation> characterInformations) {
                        //A compléter
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //A compléter
                    }
                }));
    }

    public void getAllCharacters(){
        compositeDisposable.clear();
        compositeDisposable.add(gotDisplayRepository.getAllCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<CharacterInformation>>() {

                    @Override
                    public void onSuccess(@NonNull List<CharacterInformation> characterInformations) {
                        //A compléter
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString());
                    }
                }));
    }
}
