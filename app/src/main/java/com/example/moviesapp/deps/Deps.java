package com.example.moviesapp.deps;

import com.example.moviesapp.ui.film_detail.MovieDetailActivity;
import com.example.moviesapp.networking.NetworkModule;
import com.example.moviesapp.ui.films_list.FilmsListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(FilmsListActivity filmsListActivity);
    void inject(MovieDetailActivity movieDetailActivity);
}
