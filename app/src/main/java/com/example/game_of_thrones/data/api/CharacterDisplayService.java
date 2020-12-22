package com.example.game_of_thrones.data.api;

import com.example.game_of_thrones.data.api.model.CharacterInformation;

import java.util.List;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharacterDisplayService {
    @GET("Characters")
    Single<List<CharacterInformation>> getAllCharacters();

    @GET("Characters/{id}")
    Single<List<CharacterInformation>> getCharacterById(@Path("id") int id);
}
