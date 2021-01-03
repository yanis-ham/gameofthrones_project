package com.example.game_of_thrones.presentation.display.list.mapper;

import com.example.game_of_thrones.data.api.model.CharacterInformation;
import com.example.game_of_thrones.presentation.display.list.adapter.GotCharacterInformationViewItem;

import java.util.ArrayList;
import java.util.List;

public class CharacterInformationToGotCharacterViewItem {
    public GotCharacterInformationViewItem map(CharacterInformation ci){
        GotCharacterInformationViewItem gotCharacterInformationViewItem = new GotCharacterInformationViewItem();

        gotCharacterInformationViewItem.setFullName(ci.getFullName());
        gotCharacterInformationViewItem.setFirstName(ci.getFirstName());
        gotCharacterInformationViewItem.setLastName(ci.getLastName());
        gotCharacterInformationViewItem.setTitle(ci.getTitle());
        gotCharacterInformationViewItem.setFamily(ci.getFamily());
        gotCharacterInformationViewItem.setImageUrl(ci.getImageUrl());

        return gotCharacterInformationViewItem;
    }

    public List<GotCharacterInformationViewItem> map(List<CharacterInformation> listCi){
        List<GotCharacterInformationViewItem> gotCharacterInformationViewItemsList = new ArrayList<>();
        if(listCi != null) {
            for (CharacterInformation c : listCi) {
                gotCharacterInformationViewItemsList.add(map(c));
            }
        }
        return gotCharacterInformationViewItemsList;
    }
}
