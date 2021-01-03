package com.example.game_of_thrones.data.repository;

import com.example.game_of_thrones.data.api.model.GotCharacterInformation;
import com.example.game_of_thrones.data.entity.GotCharacterEntity;
import com.example.game_of_thrones.data.repository.local.GotCharacterDisplayLocalDataSource;
import com.example.game_of_thrones.data.repository.mapper.GotCharacterInformationToGotCharacterEntityMapper;
import com.example.game_of_thrones.data.repository.remote.GotCharacterDisplayRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class GotCharacterDisplayDataRepository implements GotCharacterDisplayRepository {
    GotCharacterDisplayRemoteDataSource gotCharacterDisplayRemoteDataSource;
    GotCharacterDisplayLocalDataSource gotCharacterDisplayLocalDataSource;
    GotCharacterInformationToGotCharacterEntityMapper gotCharacterInformationToGotCharacterEntityMapper;

    public GotCharacterDisplayDataRepository(GotCharacterDisplayRemoteDataSource gotCharacterDisplayRemoteDataSource,
                                             GotCharacterDisplayLocalDataSource gotCharacterDisplayLocalDataSource){
        this.gotCharacterDisplayRemoteDataSource = gotCharacterDisplayRemoteDataSource;
        this.gotCharacterDisplayLocalDataSource = gotCharacterDisplayLocalDataSource;
        this.gotCharacterInformationToGotCharacterEntityMapper = new GotCharacterInformationToGotCharacterEntityMapper();
    }

    public Single<GotCharacterInformation> getCharacterById(int id){
        return this.gotCharacterDisplayRemoteDataSource.getCharacterById(id);
    }

    public Single<List<GotCharacterInformation>> getAllCharacters(){
        return this.gotCharacterDisplayRemoteDataSource.getAllCharacters();
    }

    @Override
    public Flowable<List<GotCharacterEntity>> getFavoriteGotCharacters() {
        return this.gotCharacterDisplayLocalDataSource.getFavoriteCharacters();
    }

    @Override
    public Completable add(int id) {
        return gotCharacterDisplayRemoteDataSource.getCharacterById(id)
                .map(new Function<GotCharacterInformation, GotCharacterEntity>() {
                    @Override
                    public GotCharacterEntity apply(@NonNull GotCharacterInformation gotCharacterInformations) throws Exception {
                        return gotCharacterInformationToGotCharacterEntityMapper.map(gotCharacterInformations);
                    }
                }).flatMapCompletable(new Function<GotCharacterEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(@NonNull GotCharacterEntity gotCharacterEntity) throws Exception {
                        return gotCharacterDisplayLocalDataSource.add(gotCharacterEntity);
                    }
                });
    }

    @Override
    public Completable remove(int id) {
        return gotCharacterDisplayLocalDataSource.remove(id);
    }
}
