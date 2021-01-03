package com.example.game_of_thrones.data.repository.mapper;

import com.example.game_of_thrones.data.api.model.GotCharacterInformation;
import com.example.game_of_thrones.data.entity.GotCharacterEntity;

public class GotCharacterInformationToGotCharacterEntityMapper {

    public GotCharacterEntity map(GotCharacterInformation gotCharacterInformation){

        GotCharacterEntity gotCharacterEntity = new GotCharacterEntity();

        gotCharacterEntity.setId(gotCharacterInformation.getId());
        gotCharacterEntity.setFullname(gotCharacterInformation.getFullName());
        gotCharacterEntity.setFirstname(gotCharacterInformation.getFirstName());
        gotCharacterEntity.setLastname(gotCharacterInformation.getLastName());
        gotCharacterEntity.setTitle(gotCharacterInformation.getTitle());
        gotCharacterEntity.setFamily(gotCharacterInformation.getFamily());
        gotCharacterEntity.setImageUrl(gotCharacterInformation.getImage());

        return gotCharacterEntity;
    }
}
