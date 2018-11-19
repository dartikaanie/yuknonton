package com.example.dara.yuknonton.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NowPlayingDao {
    //query
    @Query("Select * From playing_movies")
    //method
    List<NowPlaying> getAllNowPlaying();

    @Insert
    void insertNowPlayingMovies(NowPlaying...nowPlayings);
    //NowPlaying...nowPlayings
    //artinya bisa banyak data sekaligus. kata awal merupakan objek dan kata kedua adalah data

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNowPlayingMovie(NowPlaying nowPlayings);

}
