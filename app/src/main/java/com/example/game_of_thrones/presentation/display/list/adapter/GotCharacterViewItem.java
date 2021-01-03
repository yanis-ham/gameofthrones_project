package com.example.game_of_thrones.presentation.display.list.adapter;

public class GotCharacterViewItem {
    private int id;
    //private String fullname;
    private String image;
    private String imageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public String getFullname() {
        return fullname;
    }*/

    /*public void setFullname(String fullname) {
        this.fullname = fullname;
    }*/

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImage() {
        return image;
    }
}
