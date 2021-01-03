package com.example.game_of_thrones.data.repository;

import com.example.game_of_thrones.data.api.model.GotCharacterInformation;
import com.example.game_of_thrones.data.repository.remote.GotDisplayRemoteDataSource;

import java.util.List;
import io.reactivex.Single;

public class GotDisplayDataRepository implements GotDisplayRepository {
    GotDisplayRemoteDataSource gotDisplayRemoteDataSource;

    public GotDisplayDataRepository(GotDisplayRemoteDataSource gotDisplayRemoteDataSource){
        this.gotDisplayRemoteDataSource = gotDisplayRemoteDataSource;
    }

    public Single<GotCharacterInformation> getCharacterById(int id){
        return this.gotDisplayRemoteDataSource.getCharacterById(id);
    }

    public Single<List<GotCharacterInformation>> getAllCharacters(){
        return this.gotDisplayRemoteDataSource.getAllCharacters();
    }
}
