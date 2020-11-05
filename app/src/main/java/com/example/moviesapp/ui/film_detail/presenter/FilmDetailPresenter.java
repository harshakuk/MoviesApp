package com.example.moviesapp.ui.film_detail.presenter;

import android.content.Context;

import com.example.moviesapp.models.moviesapimodels.Movie;
import com.example.moviesapp.networking.NetworkError;
import com.example.moviesapp.networking.Service;
import com.example.moviesapp.ui.film_detail.interactor.FilmDetailInteractor;
import com.example.moviesapp.ui.film_detail.interfaces.IFilmDetail;
import com.example.moviesapp.ui.film_detail.interfaces.IFilmDetailPresenter;

public class FilmDetailPresenter implements IFilmDetailPresenter {

    Service service;
    Context context;
    IFilmDetail view;
    FilmDetailInteractor interactor;
    int movieId;


    public FilmDetailPresenter(Service service, Context context, IFilmDetail view, int movieId){
        this.service = service;
        this.context = context;
        this.view = view;
        this.interactor = new FilmDetailInteractor(service,context,this);
        this.movieId = movieId;
    }


    public void getMovieDetail(){

        interactor.getMovieDetail(movieId);

    }

    @Override
    public void getFilmDetailResponse(Movie movie) {
        view.loadFilmOK(movie);
    }

    @Override
    public void getFilmDetailKO(NetworkError e) {

    }
}
