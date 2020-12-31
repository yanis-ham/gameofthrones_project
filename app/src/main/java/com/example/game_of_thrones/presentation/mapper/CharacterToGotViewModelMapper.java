package com.example.game_of_thrones.presentation.mapper;

import com.example.game_of_thrones.data.api.model.CharacterInformation;
import com.example.game_of_thrones.presentation.adapter.GotCharacterViewItem;

import java.util.ArrayList;
import java.util.List;

public class CharacterToGotViewModelMapper {

    public GotCharacterViewItem map(CharacterInformation ci){
        GotCharacterViewItem gotCharacterViewItem = new GotCharacterViewItem();
        gotCharacterViewItem.setId(ci.getId());
        gotCharacterViewItem.setImage(ci.getImage());
        return gotCharacterViewItem;
    }

    public List<GotCharacterViewItem> map(List<CharacterInformation> listCi){
        List<GotCharacterViewItem> listGotCharacterViewItem = new ArrayList<>();
        if(listCi != null) {
            for (CharacterInformation c : listCi) {
                listGotCharacterViewItem.add(map(c));
            }
        }
        return listGotCharacterViewItem;
    }
}
