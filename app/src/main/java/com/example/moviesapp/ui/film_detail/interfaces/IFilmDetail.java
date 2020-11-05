package com.example.moviesapp.ui.film_detail.interfaces;

import com.example.moviesapp.models.moviesapimodels.Movie;
import com.example.moviesapp.networking.NetworkError;

public interface IFilmDetail {
    void loadFilmOK(Movie movie);
    void loadFilmsKO(NetworkError e);
    void showSpinner();
    void removeSpinner();
}
