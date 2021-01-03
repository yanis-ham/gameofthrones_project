package com.example.game_of_thrones.data.repository;

import com.example.game_of_thrones.data.api.model.GotCharacterInformation;

import java.util.List;
import io.reactivex.Single;

public interface GotDisplayRepository {

    Single<GotCharacterInformation> getCharacterById(int id);

    Single<List<GotCharacterInformation>> getAllCharacters();

}
