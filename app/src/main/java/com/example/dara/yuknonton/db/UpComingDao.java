package com.example.dara.yuknonton.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UpComingDao {

    //query
    @Query("Select * From up_coming")
    //method
    List<upComing> getAllUpComing();

    @Insert
    void insertUpComings(upComing...upComings);
    //NowPlaying...nowPlayings
    //artinya bisa banyak data sekaligus. kata awal merupakan objek dan kata kedua adalah data

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUpComing(upComing upComing);
}
