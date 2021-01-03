package com.example.game_of_thrones.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.game_of_thrones.data.entity.GotCharacterEntity;
import com.example.game_of_thrones.data.repository.GotCharacterDisplayRepository;
import com.example.game_of_thrones.presentation.display.favorite.adapter.FavoriteGotCharacterAdapter;
import com.example.game_of_thrones.presentation.display.favorite.adapter.FavoriteGotCharacterViewItem;
import com.example.game_of_thrones.presentation.display.favorite.mapper.GotCharacterEntityToViewItemMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class FavoriteGotCharacterViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable;
    private GotCharacterEntityToViewItemMapper gotCharacterEntityToViewItemMapper;
    private GotCharacterDisplayRepository gotCharacterDisplayRepository;

    public FavoriteGotCharacterViewModel(GotCharacterDisplayRepository gotCharacterDisplayRepository){
        this.compositeDisposable = new CompositeDisposable();
        this.gotCharacterEntityToViewItemMapper = new GotCharacterEntityToViewItemMapper();
        this.gotCharacterDisplayRepository = gotCharacterDisplayRepository;
    }

    final MutableLiveData<Event<Integer>> characterAddedEvent = new MutableLiveData<>();
    final MutableLiveData<Event<Integer>> characterDeletedEvent = new MutableLiveData<>();
    private MutableLiveData<List<FavoriteGotCharacterViewItem>> favoriteGotCharacters = new MutableLiveData<>();

    public MutableLiveData<List<FavoriteGotCharacterViewItem>> getGotCharacters(){
        return favoriteGotCharacters;
    }

    public MutableLiveData<Event<Integer>> getCharacterAddedEvent() {
        return characterAddedEvent;
    }

    public MutableLiveData<Event<Integer>> getCharacterDeletedEvent() {
        return characterDeletedEvent;
    }

    public void getFavoriteCharacters(){
        compositeDisposable.clear();
        compositeDisposable.add(gotCharacterDisplayRepository.getFavoriteGotCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<GotCharacterEntity>>() {

                    @Override
                    public void onNext(List<GotCharacterEntity> gotCharacterEntities) {
                        favoriteGotCharacters.setValue(gotCharacterEntityToViewItemMapper.map(gotCharacterEntities));
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void addCharacterToFavorites(final int id){
        compositeDisposable.add(gotCharacterDisplayRepository.add(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        characterAddedEvent.setValue(new Event<>(id));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println(e.toString());
                    }
                }));
    }

    public void deleteCharacterFromFavorites(final int id){
        compositeDisposable.add(gotCharacterDisplayRepository.remove(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        characterDeletedEvent.setValue(new Event<>(id));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println(e.toString());
                    }
                }));
    }
}
