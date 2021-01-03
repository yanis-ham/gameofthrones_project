package com.example.game_of_thrones.data.repository.remote;

import com.example.game_of_thrones.data.api.CharacterDisplayService;
import com.example.game_of_thrones.data.api.model.GotCharacterInformation;

import java.util.List;
import io.reactivex.Single;

public class GotDisplayRemoteDataSource {
    private CharacterDisplayService characterDisplayService;

    public GotDisplayRemoteDataSource(CharacterDisplayService characterDisplayService){
        this.characterDisplayService = characterDisplayService;
    }

    public Single<GotCharacterInformation> getCharacterById(int id){
        return this.characterDisplayService.getCharacterById(id);
    }

    public Single<List<GotCharacterInformation>> getAllCharacters(){
        return this.characterDisplayService.getAllCharacters();
    }

}
