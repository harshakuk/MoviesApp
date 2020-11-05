package com.example.moviesapp.ui.films_list.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.MainActivity;
import com.example.moviesapp.R;
import com.example.moviesapp.models.moviesapimodels.Movie;
import com.example.moviesapp.models.moviesapimodels.MoviesListResponse;
import com.example.moviesapp.ui.custom_views.CircleProgressBar;
import com.example.moviesapp.ui.film_detail.MovieDetailActivity;
import com.example.moviesapp.ui.films_list.FilmsListActivity;
import com.example.moviesapp.utils.UtilDates;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<Movie> movies;
    static private Context mContext;

    public interface OnItemClickListener {
        void onItemClick(Movie item);
    }
    private final OnItemClickListener listener;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv_movie_title, tv_movie_release_date,custom_progressBar_value;
        public ImageView iv_movie_image;
        public RelativeLayout film_parent_view;
        public CircleProgressBar circleProgressBar;

        public ViewHolder(View v) {
            super(v);

            tv_movie_title = (TextView) v.findViewById(R.id.tv_movie_title);
            tv_movie_release_date = (TextView) v.findViewById(R.id.tv_movie_release_date);
            iv_movie_image = (ImageView) v.findViewById(R.id.iv_movie_image);
            film_parent_view = (RelativeLayout) v.findViewById(R.id.film_parent_view);
            circleProgressBar = (CircleProgressBar) v.findViewById(R.id.custom_progressBar);
            custom_progressBar_value = (TextView) v.findViewById(R.id.custom_progressBar_value);
        }

        public void bind(final Movie item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //room_item.setBackgroundColor(Color.argb(50, 0, 0, 0));
                    listener.onItemClick(item);
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MoviesAdapter(List<Movie> movies, Context mContext,OnItemClickListener listener) {
        this.movies = movies;
        this.mContext = mContext;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie_list, parent, false);


        return new MoviesAdapter.ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MoviesAdapter.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tv_movie_title.setText(movies.get(position).getTitle());
        if(movies.get(position).getRelease_date()!=null && !movies.get(position).getRelease_date().equals(""))
            holder.tv_movie_release_date.setText(UtilDates.formatDates(movies.get(position).getRelease_date()));

        Picasso.with(mContext)
                .load("https://image.tmdb.org/t/p/w200/" + movies.get(position).getPoster_path())
                .into(holder.iv_movie_image);

        holder.bind(movies.get(position), listener);

        holder.film_parent_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra("movie_id",movies.get(position).getId());
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) mContext, v, "poster");
                mContext.startActivity(intent, options.toBundle());
            }
        });

        holder.circleProgressBar.setProgress(Float.valueOf(String.valueOf(movies.get(position).getVote_average() * 10)));
        holder.custom_progressBar_value.setText(String.format("%.0f",movies.get(position).getVote_average() * 10));

        if (movies.get(position).getVote_average() > 5) {
            holder.circleProgressBar.setColor(Color.GREEN);
        } else if (movies.get(position).getVote_average() > 3.5) {
            holder.circleProgressBar.setColor(Color.YELLOW);
        } else {
            holder.circleProgressBar.setColor(Color.RED);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return movies.size();
    }
}
