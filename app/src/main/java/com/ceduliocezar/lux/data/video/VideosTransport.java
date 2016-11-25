package com.ceduliocezar.lux.data.video;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TECBMCCS on 25/11/16.
 */

public class VideosTransport implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("results")
    private List<Video> results = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }
}
