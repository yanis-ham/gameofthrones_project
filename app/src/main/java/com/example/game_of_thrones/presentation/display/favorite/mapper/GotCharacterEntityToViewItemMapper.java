package com.example.game_of_thrones.presentation.display.favorite.mapper;

import com.example.game_of_thrones.data.entity.GotCharacterEntity;
import com.example.game_of_thrones.presentation.display.favorite.adapter.FavoriteGotCharacterViewItem;

import java.util.ArrayList;
import java.util.List;

public class GotCharacterEntityToViewItemMapper {

    public FavoriteGotCharacterViewItem map(GotCharacterEntity gotCharacterEntity){
        FavoriteGotCharacterViewItem favoriteGotCharacterViewItem = new FavoriteGotCharacterViewItem();

        favoriteGotCharacterViewItem.setId(gotCharacterEntity.getId());
        favoriteGotCharacterViewItem.setFullname(gotCharacterEntity.getFullname());
        favoriteGotCharacterViewItem.setImageUrl(gotCharacterEntity.getImageUrl());
        return favoriteGotCharacterViewItem;
    }

    public List<FavoriteGotCharacterViewItem> map(List<GotCharacterEntity> gotCharacterEntityList){
        List<FavoriteGotCharacterViewItem> favoriteList = new ArrayList<>();
        if(gotCharacterEntityList != null){
            for (GotCharacterEntity gotCharacterEntity : gotCharacterEntityList){
                favoriteList.add(map(gotCharacterEntity));
            }
        }
        return favoriteList;
    }
}
