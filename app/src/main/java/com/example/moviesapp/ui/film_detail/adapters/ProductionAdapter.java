package com.example.moviesapp.ui.film_detail.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;
import com.example.moviesapp.models.moviesapimodels.Movie;
import com.example.moviesapp.models.moviesapimodels.MoviesListResponse;
import com.example.moviesapp.models.moviesapimodels.ProductionCompanies;
import com.example.moviesapp.utils.UtilDates;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductionAdapter extends RecyclerView.Adapter<ProductionAdapter.ViewHolder> {
    private List<ProductionCompanies> companies;
    static private Context mContext;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title, desc;
        public ImageView image;

        public ViewHolder(View v) {
            super(v);

            title = (TextView) v.findViewById(R.id.title);
            desc = (TextView) v.findViewById(R.id.desc);
            image = (ImageView) v.findViewById(R.id.image);
        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ProductionAdapter(List<ProductionCompanies> companies, Context mContextr) {
        this.companies = companies;
        this.mContext = mContext;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.production_item, parent, false);


        return new ProductionAdapter.ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.title.setText(companies.get(position).getName());
        holder.desc.setText(companies.get(position).getOrigin_country());

        Picasso.with(mContext)
                .load("https://image.tmdb.org/t/p/w200/" + companies.get(position).getLogo_path())
                .into(holder.image);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        System.out.println("size in adapter "+companies.size());
        return companies.size();
    }
}
