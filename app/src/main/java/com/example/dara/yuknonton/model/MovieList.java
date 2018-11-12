package com.example.dara.yuknonton.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieList {
    @SerializedName("results")
    public List<MovieItem> results;
}
