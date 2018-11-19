package com.example.dara.yuknonton.db;

import android.annotation.SuppressLint;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "playing_movies")
public class NowPlaying {

    @PrimaryKey
    public  int uid;

    @ColumnInfo(name="title")
    public String title;

    @ColumnInfo(name="overview")
    public String overview;

    @ColumnInfo(name="realease_date")
    public String realease_date;

    @ColumnInfo(name="vote_average")
    public  double vote_average;

    @ColumnInfo(name = "poster_path")
    public String poster_path;

    public int id;
}
