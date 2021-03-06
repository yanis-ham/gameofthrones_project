package com.example.game_of_thrones.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.game_of_thrones.data.api.model.GotCharacterInformation;
import com.example.game_of_thrones.data.repository.GotCharacterDisplayRepository;
import com.example.game_of_thrones.presentation.display.list.adapter.GotCharacterInformationViewItem;
import com.example.game_of_thrones.presentation.display.list.adapter.GotCharacterViewItem;
import com.example.game_of_thrones.presentation.display.list.mapper.CharacterInformationToGotCharacterViewItem;
import com.example.game_of_thrones.presentation.display.list.mapper.CharacterToGotViewModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class GotCharacterViewModel extends ViewModel{

    private CharacterToGotViewModelMapper characterToGotViewModelMapper;
    private CharacterInformationToGotCharacterViewItem characterInformationToGotCharacterViewItem;
    private GotCharacterDisplayRepository gotCharacterDisplayRepository;
    private CompositeDisposable compositeDisposable;


    public GotCharacterViewModel(GotCharacterDisplayRepository gotCharacterDisplayRepository){
        this.gotCharacterDisplayRepository = gotCharacterDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.characterToGotViewModelMapper = new CharacterToGotViewModelMapper();
        this.characterInformationToGotCharacterViewItem = new CharacterInformationToGotCharacterViewItem();
    }

    private MutableLiveData<List<GotCharacterViewItem>> listCharacters = new MutableLiveData<>();

    private MutableLiveData<GotCharacterInformationViewItem> charactersInfo = new MutableLiveData<>();

    public MutableLiveData<List<GotCharacterViewItem>> getListCharacters() {
        return listCharacters;
    }

    public MutableLiveData<GotCharacterInformationViewItem> getCharactersInfo() {
        return charactersInfo;
    }

    public void getCharacterById(int id){
        compositeDisposable.clear();
        compositeDisposable.add(gotCharacterDisplayRepository.getCharacterById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<GotCharacterInformation>(){

                    @Override
                    public void onSuccess(@NonNull GotCharacterInformation gotCharacterInformation) {
                        charactersInfo.setValue(characterInformationToGotCharacterViewItem.map(gotCharacterInformation));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println(e.toString());
                    }
                }));
    }

    public void getAllCharacters(){
        compositeDisposable.clear();
        compositeDisposable.add(gotCharacterDisplayRepository.getAllCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<GotCharacterInformation>>() {

                    @Override
                    public void onSuccess(@NonNull List<GotCharacterInformation> listGotCharacterInformation) {
                        listCharacters.setValue(characterToGotViewModelMapper.map(listGotCharacterInformation));                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString());
                    }
                }));
    }
}
