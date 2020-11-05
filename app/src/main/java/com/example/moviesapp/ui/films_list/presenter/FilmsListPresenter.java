package com.example.moviesapp.ui.films_list.presenter;

import android.content.Context;

import com.example.moviesapp.models.moviesapimodels.Movie;
import com.example.moviesapp.models.moviesapimodels.MoviesListResponse;
import com.example.moviesapp.networking.NetworkError;
import com.example.moviesapp.networking.Service;
import com.example.moviesapp.ui.films_list.interactor.FilmsListInteractor;
import com.example.moviesapp.ui.films_list.interfaces.IFilmsListPresenter;
import com.example.moviesapp.ui.films_list.interfaces.IFilmsListView;

import java.util.ArrayList;
import java.util.List;

public class FilmsListPresenter implements IFilmsListPresenter {

    Service service;
    Context context;
    IFilmsListView view;
    FilmsListInteractor interactor;


    public FilmsListPresenter(Service service, Context context, IFilmsListView view){
        this.service = service;
        this.context = context;
        this.view = view;
        this.interactor = new FilmsListInteractor(service, context, this);
    }


    public void getPopularMovies(int pageCount){

        interactor.getPopularMovies(pageCount);

    }


    @Override
    public void getPopularFilmsOK(MoviesListResponse movies) {
        view.loadFilmsOK(movies);
    }

    @Override
    public void getPopularFilmsKO(NetworkError e) {
      view.loadFilmsKO(e);
    }
}
