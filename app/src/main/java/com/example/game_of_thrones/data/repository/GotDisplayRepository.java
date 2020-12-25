package com.example.game_of_thrones.data.repository;

import com.example.game_of_thrones.data.api.model.CharacterInformation;

import java.util.List;
import io.reactivex.Single;

public interface GotDisplayRepository {

    Single<List<CharacterInformation>> getCharacterById(int id);

    Single<List<CharacterInformation>> getAllCharacters();

}
