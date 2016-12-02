package com.ceduliocezar.lux.presentation.movie.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.genre.Genre;
import com.ceduliocezar.lux.data.movie.Movie;
import com.ceduliocezar.lux.data.poster.PosterProvider;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ceduliocezar.lux.R.id.movie_year;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ceduliocezar on 02/12/16.
 */

public class MovieAdapter extends BaseAdapter {

    private final PosterProvider posterProvider;
    private List<Movie> movies;
    private List<Genre> genres;

    public MovieAdapter(List<Movie> movies, List<Genre> genres, PosterProvider posterProvider) {
        this.movies = checkNotNull(movies, "movies can not be null");
        this.genres = checkNotNull(genres, "genres can not be null");
        this.posterProvider = checkNotNull(posterProvider, "poster provider can not be null");
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return movies.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Movie movie = movies.get(position);

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        initImageView(viewHolder, movie);
        initGenreView(viewHolder, movie);
        initTitleView(viewHolder, movie);
        initYearView(viewHolder, movie);
        initRateView(viewHolder, movie);

        return convertView;
    }

    private void initRateView(ViewHolder holder, Movie movie) {
        holder.tvRateNumber.setText(String.valueOf(movie.getVoteAverage()));
    }

    private void initYearView(ViewHolder holder, Movie movie) {
        holder.tvYear.setText(getReleaseYear(movie));
    }

    private void initTitleView(ViewHolder holder, Movie movie) {
        holder.tvTitle.setText(movie.getTitle());
    }

    private void initGenreView(ViewHolder holder, Movie movie) {
        holder.tvGenres.setText(getGenres(movie));
    }

    private void initImageView(ViewHolder holder, Movie movie) {
        posterProvider.loadImage(movie.getPosterPath(), holder.imageView, holder.imageView.getContext());
    }

    private String getReleaseYear(Movie movie) {


        if (movie.getReleaseDate().isEmpty()) {
            return "";
        }

        return movie.getReleaseDate().substring(0, 4);
    }

    public void addAll(List<Movie> movies) {
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    public void clear() {
        this.movies.clear();
    }

    private String getGenres(Movie movie) {


        List<String> genresString = new ArrayList<>();
        for (Genre genre : genres) {
            for (Integer id : movie.getGenreIds()) {
                if (genre.getId() == id) {
                    genresString.add(genre.getName());
                }
            }
        }
        return StringUtils.join(genresString, ", ");
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    static class ViewHolder {
        @BindView(R.id.movie_rate_number)
        TextView tvRateNumber;

        @BindView(movie_year)
        TextView tvYear;

        @BindView(R.id.movie_title)
        TextView tvTitle;

        @BindView(R.id.movie_genre)
        TextView tvGenres;

        @BindView(R.id.movie_image)
        ImageView imageView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}