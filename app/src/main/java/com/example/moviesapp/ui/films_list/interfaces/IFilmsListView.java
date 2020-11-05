package com.example.moviesapp.ui.films_list.interfaces;

import com.example.moviesapp.models.moviesapimodels.MoviesListResponse;
import com.example.moviesapp.networking.NetworkError;

public interface IFilmsListView {

    void loadFilmsOK(MoviesListResponse movies);
    void loadFilmsKO(NetworkError e);
    void showSpinner();
    void removeSpinner();

}
