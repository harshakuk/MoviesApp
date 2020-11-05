package com.example.moviesapp.networking;

import com.example.moviesapp.models.moviesapimodels.Movie;
import com.example.moviesapp.models.moviesapimodels.MoviesListResponse;
import com.example.moviesapp.utils.Constants;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class Service {


    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getPopularMoviesList(final GetPopularMoviesListCallback callback,int pageCount) {


        return networkService.getPopularMoviesList(Constants.getAPI_KEY(), "en-EN", pageCount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends MoviesListResponse>>() {
                    @Override
                    public Observable<? extends MoviesListResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<MoviesListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        new NetworkError(e).getAppErrorMessage();
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(MoviesListResponse popularMoviesListResponse) {
                        callback.onSuccess(popularMoviesListResponse);

                    }
                });
    }

    public Subscription getMovieDetail(final Service.GetMovieDetailCallback callback, int movieId) {


        return networkService.getMovieDetail(movieId,Constants.getAPI_KEY(), "en-EN")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Movie>>() {
                    @Override
                    public Observable<? extends Movie> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<Movie>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        new NetworkError(e).getAppErrorMessage();
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(Movie movie) {
                        callback.onSuccess(movie);

                    }
                });
    }

    public interface GetPopularMoviesListCallback{
        void onSuccess(MoviesListResponse popularMoviesListResponse);
        void onError(NetworkError e);
    }

    public interface GetMovieDetailCallback{
        void onSuccess(Movie movie);
        void onError(NetworkError e);
    }

}
