package com.example.moviesapp.ui.films_list;

import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.moviesapp.BaseApp;
import com.example.moviesapp.networking.NetworkError;
import com.example.moviesapp.ui.film_detail.MovieDetailActivity;
import com.example.moviesapp.R;
import com.example.moviesapp.models.moviesapimodels.Movie;
import com.example.moviesapp.models.moviesapimodels.MoviesListResponse;
import com.example.moviesapp.networking.Service;
import com.example.moviesapp.ui.films_list.adapters.MoviesAdapter;
import com.example.moviesapp.ui.films_list.interfaces.IFilmsListView;
import com.example.moviesapp.ui.films_list.presenter.FilmsListPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilmsListActivity extends BaseApp implements IFilmsListView {

    @Inject
    public Service service;

    @BindView(R.id.rv_movies)
    RecyclerView rv_movies;

    private FilmsListPresenter presenter;
    private Parcelable recyclerViewState;

    private MoviesListResponse movies;
    ProgressBar progress_bar_circle_pro;
    private MoviesAdapter adapter;
    int pageCount = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        setContentView(R.layout.activity_films_list);

        ButterKnife.bind(this);
        progress_bar_circle_pro = findViewById(R.id.progress_bar_circle_pro);

        presenter = new FilmsListPresenter(service, getApplicationContext(), this);

        renderView();
    }

    private void renderView(){

        rv_movies.setLayoutManager(new GridLayoutManager(this, 2));
        //rv_movies.setHasFixedSize(true);
        presenter.getPopularMovies(pageCount);
        progress_bar_circle_pro.setVisibility(View.VISIBLE);

        rv_movies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    pageCount = pageCount+1;
                    presenter.getPopularMovies(pageCount);
                    progress_bar_circle_pro.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public void loadFilmsOK(MoviesListResponse movies) {
        progress_bar_circle_pro.setVisibility(View.GONE);
        if(pageCount == 1){
            this.movies = movies;
        }
        else if(pageCount <= movies.getTotal_pages() && pageCount!=1)
        {
            recyclerViewState = rv_movies.getLayoutManager().onSaveInstanceState();
            for(int i=0;i<movies.getResults().size();i++){
                this.movies.getResults().add(movies.getResults().get(i));
            }
            adapter.notifyDataSetChanged();
            rv_movies.getLayoutManager().onRestoreInstanceState(recyclerViewState);
        }

        adapter = new MoviesAdapter(this.movies.getResults(), this, new MoviesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie item) {

            }
        });
        rv_movies.setAdapter(adapter);

    }

    @Override
    public void loadFilmsKO(NetworkError e) {

        Toast.makeText(this,e.getAppErrorMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSpinner() {

    }

    @Override
    public void removeSpinner() {

    }
}
