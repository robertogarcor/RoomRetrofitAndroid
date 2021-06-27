package com.example.rgc.roomretrofitandroid.data.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies() : LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(movies: List<Movie>)

    @Query("SELECT count(id) FROM movies")
    fun countMovies() : Int

}