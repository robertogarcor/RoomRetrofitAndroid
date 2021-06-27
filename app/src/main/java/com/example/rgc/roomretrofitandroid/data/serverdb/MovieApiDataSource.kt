package com.example.rgc.roomretrofitandroid.data.serverdb

import com.example.rgc.roomretrofitandroid.data.domain.model.Movie
import com.example.rgc.roomretrofitandroid.data.domain.repository.RemoteDataSource
import com.example.rgc.roomretrofitandroid.data.toDomainMovie


class MovieApiDataSource(var apiKey: String) : RemoteDataSource {

   /*
   override suspend fun getMovies(page: Int): List<Movie> =
        MovieApiDb.retrofit
            .getListMoviesPopular(apiKey, page)
            .results.map { it.toDomainMovie() }
   */


   override suspend fun getMovies(page: Int): List<Movie>? {
        val call = MovieApiDb.retrofit.getListMoviesPopular(apiKey, page)
        val res = call.execute().body()?.results
        var results : List<Movie>? = null
        if (res != null) {
            results = res.map { it.toDomainMovie() }
        }
        return results
   }
}
