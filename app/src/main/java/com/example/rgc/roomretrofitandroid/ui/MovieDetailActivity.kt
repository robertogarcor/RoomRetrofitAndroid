package com.example.rgc.roomretrofitandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.rgc.roomretrofitandroid.R
import com.example.rgc.roomretrofitandroid.databinding.ActivityMovieDetailBinding
import com.example.rgc.roomretrofitandroid.data.domain.model.Movie

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle(R.string.act_toolbar_detail_movie_title)

        val movie = intent.getSerializableExtra("MOVIE") as Movie

        with(binding) {
            textViewTitleDetailMovie.text = movie.title
            ratingBarVoteAverageMovie.rating = movie.voteAverage.toFloat() / 2
            textViewVoteAverageDetailMovie.text = StringBuilder("${movie.voteAverage} / 10")
            textViewOverviewDetailMovie.text = movie.overview
            Glide.with(this@MovieDetailActivity)
                .load("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
                .into(imageViewDetailMovie);
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

}