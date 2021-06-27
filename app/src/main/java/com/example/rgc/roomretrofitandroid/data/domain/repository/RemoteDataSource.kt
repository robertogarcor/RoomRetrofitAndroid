package com.example.rgc.roomretrofitandroid.data.domain.repository

import com.example.rgc.roomretrofitandroid.data.domain.model.Movie

interface RemoteDataSource {
    suspend fun getMovies(page: Int) : List<Movie>?
}