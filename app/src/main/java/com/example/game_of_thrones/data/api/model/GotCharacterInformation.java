package com.example.game_of_thrones.data.api.model;

import com.google.gson.annotations.SerializedName;

public class GotCharacterInformation {

    @SerializedName("id")
    private int id;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("title")
    private String title;

    @SerializedName("family")
    private String family;

    @SerializedName("image")
    private String image;

    @SerializedName("imageUrl")
    private String imageUrl;

    public int getId() { return id; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTitle() {
        return title;
    }

    public String getFamily() {
        return family;
    }

    public String getImage() {
        return image;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
