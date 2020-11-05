package com.example.moviesapp.ui.films_list.interfaces;

import com.example.moviesapp.models.moviesapimodels.Movie;
import com.example.moviesapp.models.moviesapimodels.MoviesListResponse;
import com.example.moviesapp.networking.NetworkError;

import java.util.ArrayList;
import java.util.List;

public interface IFilmsListPresenter {

    void getPopularFilmsOK(MoviesListResponse movies);
    void getPopularFilmsKO(NetworkError e);

}
