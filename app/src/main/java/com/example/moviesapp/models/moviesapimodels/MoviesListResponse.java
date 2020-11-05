package com.example.moviesapp.models.moviesapimodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class MoviesListResponse extends RealmObject {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer total_results;
    @SerializedName("total_pages")
    @Expose
    private Integer total_pages;
    @SerializedName("results")
    @Expose
    private RealmList<Movie> results;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public RealmList<Movie> getResults() {
        return results;
    }

    public void setResults(RealmList<Movie> results) {
        this.results = results;
    }
}
