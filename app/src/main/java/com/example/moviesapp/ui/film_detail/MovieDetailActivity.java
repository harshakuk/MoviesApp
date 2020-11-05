package com.example.moviesapp.ui.film_detail;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.BaseApp;
import com.example.moviesapp.R;
import com.example.moviesapp.models.moviesapimodels.Movie;
import com.example.moviesapp.networking.NetworkError;
import com.example.moviesapp.networking.Service;
import com.example.moviesapp.ui.custom_views.CircleProgressBar;
import com.example.moviesapp.ui.film_detail.adapters.ProductionAdapter;
import com.example.moviesapp.ui.film_detail.interfaces.IFilmDetail;
import com.example.moviesapp.ui.film_detail.presenter.FilmDetailPresenter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends BaseApp implements IFilmDetail {

    @Inject
    public Service service;
    FilmDetailPresenter filmDetailPresenter;

    @BindView(R.id.overview)
    TextView overview;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.release_date)
    TextView release_date;
    @BindView(R.id.length)
    TextView length;
    @BindView(R.id.genre)
    TextView genre;
    @BindView(R.id.custom_progressBar_value)
    TextView custom_progressBar_value;
    @BindView(R.id.iv_movie_image)
    ImageView iv_movie_image;
    @BindView(R.id.poster_image)
    ImageView poster_image;
    @BindView(R.id.cv_movie_poster)
    CardView cv_movie_poster;
    @BindView(R.id.cast)
    RecyclerView cast;
    @BindView(R.id.custom_progressBar)
    CircleProgressBar circleProgressBar;

    ProductionAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        renderView();

    }

    private void renderView(){

        cv_movie_poster.setClipToOutline(false);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        cast.setLayoutManager(layoutManager);
        int id = getIntent().getIntExtra("movie_id",0);
        filmDetailPresenter = new FilmDetailPresenter(service,getBaseContext(),this,id);
        filmDetailPresenter.getMovieDetail();

    }

    @Override
    public void loadFilmOK(Movie movie) {

        overview.setText(movie.getOverview());
        title.setText(movie.getTitle());
        release_date.setText(movie.getRelease_date());
        length.setText(movie.getRuntime()+" "+"min");
        String str="";
        for(int i=0;i<movie.getGenres().size();i++){
            str = str + " " + movie.getGenres().get(i).getName();
        }
        genre.setText(str);

        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w200/" + movie.getBackdrop_path())
                .into(iv_movie_image);

        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w200/" + movie.getPoster_path())
                .into(poster_image);

        circleProgressBar.setProgress(Float.valueOf(String.valueOf(movie.getVote_average() * 10)));
        custom_progressBar_value.setText(String.format("%.0f",movie.getVote_average() * 10));

        if (movie.getVote_average() > 5) {
            circleProgressBar.setColor(Color.GREEN);
        } else if (movie.getVote_average() > 3.5) {
            circleProgressBar.setColor(Color.YELLOW);
        } else {
            circleProgressBar.setColor(Color.RED);
        }
        System.out.println("production "+movie.getProduction_companies().size());
        adapter = new ProductionAdapter(movie.getProduction_companies(), this);
        cast.setAdapter(adapter);

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
