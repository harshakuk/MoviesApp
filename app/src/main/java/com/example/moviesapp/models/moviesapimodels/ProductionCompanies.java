package com.example.moviesapp.models.moviesapimodels;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class ProductionCompanies extends RealmObject {
    @SerializedName("id")
    int id;
    @SerializedName("logo_path")
    String logo_path;
    @SerializedName("name")
    String name;
    @SerializedName("origin_country")
    String origin_country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogo_path() {
        return logo_path;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }
}
