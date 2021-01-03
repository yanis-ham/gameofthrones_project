package com.example.game_of_thrones.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.game_of_thrones.data.entity.GotCharacterEntity;

@Database(entities = {GotCharacterEntity.class}, version = 1)
public abstract class GotCharacterDatabase extends RoomDatabase {
    public abstract GotCharacterDao gotCharacterDao();
}