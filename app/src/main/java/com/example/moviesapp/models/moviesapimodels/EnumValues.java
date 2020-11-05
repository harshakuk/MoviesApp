package com.example.moviesapp.models.moviesapimodels;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class EnumValues extends RealmObject {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
