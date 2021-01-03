package com.example.game_of_thrones.presentation.display.list.mapper;

import com.example.game_of_thrones.data.api.model.GotCharacterInformation;
import com.example.game_of_thrones.presentation.display.list.adapter.GotCharacterViewItem;

import java.util.ArrayList;
import java.util.List;

public class CharacterToGotViewModelMapper {

    public GotCharacterViewItem map(GotCharacterInformation ci){
        GotCharacterViewItem gotCharacterViewItem = new GotCharacterViewItem();
        gotCharacterViewItem.setId(ci.getId());
        gotCharacterViewItem.setImageUrl(ci.getImageUrl());
        return gotCharacterViewItem;
    }

    public List<GotCharacterViewItem> map(List<GotCharacterInformation> listCi){
        List<GotCharacterViewItem> listGotCharacterViewItem = new ArrayList<>();
        if(listCi != null) {
            for (GotCharacterInformation c : listCi) {
                listGotCharacterViewItem.add(map(c));
            }
        }
        return listGotCharacterViewItem;
    }
}
