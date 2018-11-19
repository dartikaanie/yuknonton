package com.example.dara.yuknonton.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieItem implements Parcelable{
    int id;
    String title;
    String overview;
    double vote_average;
    String release_date;
    String poster_path;

       @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeDouble(this.vote_average);
        dest.writeString(this.release_date);
        dest.writeString(this.poster_path);
    }

    protected MovieItem(Parcel in){
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.vote_average = in.readDouble();
        this.release_date = in.readString();
        this.poster_path = in.readString();
    }

    public static final Parcelable.Creator<MovieItem> CREATOR = new Parcelable.Creator<MovieItem>(){
        @Override
        public MovieItem createFromParcel(Parcel source){
            return new MovieItem(source);
        }

        @Override
        public  MovieItem[] newArray(int size){
            return new MovieItem[size];
        }
    };

    public MovieItem(int id, String title, String overview, double vote_average, String release_date, String poster_path) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.poster_path = poster_path;
    }



    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }
}
