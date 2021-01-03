package com.example.game_of_thrones.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.game_of_thrones.data.entity.GotCharacterEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface GotCharacterDao {

    @Query("SELECT * FROM gotCharacters")
    Flowable<List<GotCharacterEntity>> getFavorites();

    @Query("SELECT id from gotCharacters")
    Single<List<Integer>> getFavoriteIdList();

    @Insert
    Completable add(GotCharacterEntity gotCharacterEntity);

    @Query("DELETE FROM gotCharacters WHERE id = :id")
    Completable delete(int id);

}
