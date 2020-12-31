package com.example.game_of_thrones.data.api.model;

import com.google.gson.annotations.SerializedName;

public class CharacterInformation {

    @SerializedName("id")
    private int id;

    @SerializedName("firstname")
    private String firstName;

    @SerializedName("lastname")
    private String lastName;

    @SerializedName("fullname")
    private String fullName;

    @SerializedName("title")
    private String title;

    @SerializedName("family")
    private String family;

    @SerializedName("image")
    private String image;

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

}
