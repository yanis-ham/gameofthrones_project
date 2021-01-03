package com.example.game_of_thrones.data.api;

import com.example.game_of_thrones.data.api.model.GotCharacterInformation;

import java.util.List;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GotCharacterDisplayService {
    @GET("Characters")
    Single<List<GotCharacterInformation>> getAllCharacters();

    @GET("Characters/{id}")
    Single<GotCharacterInformation> getCharacterById(@Path("id") int id);
}
