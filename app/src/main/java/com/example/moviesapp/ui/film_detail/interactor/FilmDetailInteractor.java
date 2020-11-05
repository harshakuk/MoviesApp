package com.example.moviesapp.ui.film_detail.interactor;

import android.content.Context;
import android.util.Log;

import com.example.moviesapp.models.moviesapimodels.Movie;
import com.example.moviesapp.networking.NetworkError;
import com.example.moviesapp.networking.Service;
import com.example.moviesapp.ui.film_detail.interfaces.IFilmDetailPresenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class FilmDetailInteractor {
    Context context;
    IFilmDetailPresenter presenter;

    private Service service;
    private CompositeSubscription subscriptions;

    public FilmDetailInteractor(Service service, Context context, IFilmDetailPresenter presenter){
        this.service = service;
        this.context = context;
        this.presenter = presenter;
        this.subscriptions = new CompositeSubscription();
    }

    public void getMovieDetail(int movieId){
        Subscription subscription = service.getMovieDetail(new Service.GetMovieDetailCallback() {
            @Override
            public void onSuccess(Movie movie) {


                Log.e("error", movie+" <------------------");
                Log.e("error", "OK!");

                presenter.getFilmDetailResponse(movie);

            }

            @Override
            public void onError(NetworkError e) {
                Log.e("error", "ERROR!");
                presenter.getFilmDetailKO(e);
            }

        },movieId);

        subscriptions.add(subscription);
    }
}
