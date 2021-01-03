package com.example.game_of_thrones.data.repository;

import com.example.game_of_thrones.data.api.model.GotCharacterInformation;
import com.example.game_of_thrones.data.entity.GotCharacterEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface GotCharacterDisplayRepository {

    Single<GotCharacterInformation> getCharacterById(int id);

    Single<List<GotCharacterInformation>> getAllCharacters();

    Flowable<List<GotCharacterEntity>> getFavoriteGotCharacters();

    Completable add(int id);

    Completable remove(int id);

}
