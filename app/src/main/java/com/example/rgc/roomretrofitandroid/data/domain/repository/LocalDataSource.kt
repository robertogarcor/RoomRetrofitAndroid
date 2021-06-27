package com.example.rgc.roomretrofitandroid.data.domain.repository

import androidx.lifecycle.LiveData
import com.example.rgc.roomretrofitandroid.data.domain.model.Movie

interface LocalDataSource {
    fun getMovies() : LiveData<List<Movie>>
    suspend fun saveMovies(movies : List<Movie>)
    suspend fun size() : Int
}