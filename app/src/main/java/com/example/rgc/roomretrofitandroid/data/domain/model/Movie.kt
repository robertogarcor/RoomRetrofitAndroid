package com.example.rgc.roomretrofitandroid.data.domain.model

import java.io.Serializable

data class Movie(
                 val id: Int,
                 val title: String,
                 val overview: String,
                 val posterPath: String?,
                 val voteAverage: Double) : Serializable
