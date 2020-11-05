package com.example.moviesapp.ui.films_list.interactor;

import android.content.Context;
import android.util.Log;

import com.example.moviesapp.models.moviesapimodels.MoviesListResponse;
import com.example.moviesapp.networking.NetworkError;
import com.example.moviesapp.networking.Service;
import com.example.moviesapp.ui.films_list.interfaces.IFilmsListPresenter;
import com.example.moviesapp.ui.films_list.interfaces.IFilmsListView;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class FilmsListInteractor {

    Context context;
    IFilmsListPresenter presenter;

    private Service service;
    private CompositeSubscription subscriptions;

    public FilmsListInteractor(Service service, Context context, IFilmsListPresenter presenter){
        this.service = service;
        this.context = context;
        this.presenter = presenter;
        this.subscriptions = new CompositeSubscription();
    }

    public void getPopularMovies(int pageCount){
        Subscription subscription = service.getPopularMoviesList(new Service.GetPopularMoviesListCallback() {
            @Override
            public void onSuccess(MoviesListResponse fruitListResponse) {


                Log.e("error", fruitListResponse+" <------------------");
                Log.e("error", "OK!");

                presenter.getPopularFilmsOK(fruitListResponse);

            }

            @Override
            public void onError(NetworkError e) {
                Log.e("error", "ERROR!");
                presenter.getPopularFilmsKO(e);
            }

        },pageCount);

        subscriptions.add(subscription);
    }
}