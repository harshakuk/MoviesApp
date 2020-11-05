package com.example.moviesapp.networking;

import com.example.moviesapp.models.moviesapimodels.Movie;
import com.example.moviesapp.models.moviesapimodels.MoviesListResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by harshakukreja on 05/11/2020.
 */

public interface NetworkService {

    String API_KEY = "api_key";
    String LANGUAGE = "language";
    String PAGE = "page";

    @GET("movie/popular")
    Observable<MoviesListResponse> getPopularMoviesList(
            @Query(API_KEY) String api_key,
            @Query(LANGUAGE) String language,
            @Query(PAGE) Integer page);

    @GET("movie/{movie_id}")
    Observable<Movie> getMovieDetail(
            @Path("movie_id") int movie_id,
            @Query(API_KEY) String api_key,
            @Query(LANGUAGE) String language
            );
}
