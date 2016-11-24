package com.ceduliocezar.lux.data.genre;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GenreTransport {

    @SerializedName("genres")
    @Expose
    private List<Genre> genres = new ArrayList<>();


    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
