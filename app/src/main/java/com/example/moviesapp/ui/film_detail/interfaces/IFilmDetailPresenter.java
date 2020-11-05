package com.example.moviesapp.ui.film_detail.interfaces;

import com.example.moviesapp.models.moviesapimodels.Movie;
import com.example.moviesapp.networking.NetworkError;

public interface IFilmDetailPresenter {
    void getFilmDetailResponse(Movie movies);
    void getFilmDetailKO(NetworkError e);
}
