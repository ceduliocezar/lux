package com.ceduliocezar.lux.menu.genre;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.cloud.Genre;

import java.util.List;

/**
 * Created by cedulio on 05/06/16.
 */
public class MoviesGenreAdapter  extends RecyclerView.Adapter<MoviesGenreAdapter.ViewHolder> {


    private final List<Genre> genres;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.genre_name);
        }
    }

    public MoviesGenreAdapter(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public MoviesGenreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_genre, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(genres.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }
}

