package com.example.game_of_thrones.data.repository.remote;

import com.example.game_of_thrones.data.api.GotCharacterDisplayService;
import com.example.game_of_thrones.data.api.model.GotCharacterInformation;

import java.util.List;
import io.reactivex.Single;

public class GotCharacterDisplayRemoteDataSource {
    private GotCharacterDisplayService gotCharacterDisplayService;

    public GotCharacterDisplayRemoteDataSource(GotCharacterDisplayService gotCharacterDisplayService){
        this.gotCharacterDisplayService = gotCharacterDisplayService;
    }

    public Single<GotCharacterInformation> getCharacterById(int id){
        return this.gotCharacterDisplayService.getCharacterById(id);
    }

    public Single<List<GotCharacterInformation>> getAllCharacters(){
        return this.gotCharacterDisplayService.getAllCharacters();
    }

}
