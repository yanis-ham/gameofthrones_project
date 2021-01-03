package com.example.game_of_thrones.data.repository.local;

import com.example.game_of_thrones.data.db.GotCharacterDatabase;
import com.example.game_of_thrones.data.entity.GotCharacterEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class GotCharacterDisplayLocalDataSource {

    private GotCharacterDatabase gotCharacterDatabase;

    public GotCharacterDisplayLocalDataSource(GotCharacterDatabase gotCharacterDatabase){
        this.gotCharacterDatabase = gotCharacterDatabase;
    }

    public Flowable<List<GotCharacterEntity>> getFavoriteCharacters(){
        return gotCharacterDatabase.gotCharacterDao().getFavorites();
    }

    public Completable add(GotCharacterEntity entity){
        return gotCharacterDatabase.gotCharacterDao().add(entity);
    }

    public Completable remove(int id){
        return gotCharacterDatabase.gotCharacterDao().delete(id);
    }
}
