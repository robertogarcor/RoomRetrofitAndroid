package com.example.rgc.roomretrofitandroid

import android.app.Application
import androidx.room.Room
import com.example.rgc.roomretrofitandroid.data.roomdb.MovieDatabase

class MoviesApp : Application() {

    lateinit var roomdb: MovieDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        // Get Instance Room DB
        roomdb = Room.databaseBuilder(
            this,
            MovieDatabase::class.java,
            "movies-db"
        ).build()
    }

}