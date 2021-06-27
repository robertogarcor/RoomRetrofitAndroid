package com.example.rgc.roomretrofitandroid.data

import com.example.rgc.roomretrofitandroid.data.domain.model.Movie
import com.example.rgc.roomretrofitandroid.data.roomdb.Movie as RoomMovie
import com.example.rgc.roomretrofitandroid.data.serverdb.Movie as ServerMovie

fun ServerMovie.toDomainMovie(): Movie =
    Movie(
        0,
        title,
        overview,
        posterPath,
        voteAverage
    )

fun Movie.toRoomMovie(): RoomMovie =
    RoomMovie(
        id,
        title,
        overview,
        posterPath,
        voteAverage
    )

fun RoomMovie.toDomainMovie(): Movie =
    Movie(
        id,
        title,
        overview,
        posterPath,
        voteAverage
    )