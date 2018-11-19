package com.example.dara.yuknonton.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {NowPlaying.class, upComing.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    //akses ke Dao
    public abstract NowPlayingDao nowPlayingDao();
    public abstract UpComingDao upComingDao();

}
