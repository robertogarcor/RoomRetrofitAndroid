package com.example.rgc.roomretrofitandroid.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rgc.roomretrofitandroid.R
import com.example.rgc.roomretrofitandroid.data.domain.repository.MovieRepositoryImpl
import com.example.rgc.roomretrofitandroid.data.roomdb.MovieRoomDataSource
import com.example.rgc.roomretrofitandroid.data.serverdb.MovieApiDataSource
import com.example.rgc.roomretrofitandroid.ui.adapter.MovieAdapter
import com.example.rgc.roomretrofitandroid.databinding.ActivityMainBinding
import com.example.rgc.roomretrofitandroid.ui.custom.app

import com.example.rgc.roomretrofitandroid.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding : ActivityMainBinding
    private val TAG =  "MainActivity_Items_Scroll"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setTitle(R.string.app_main_name)

        viewModel = getViewModel { buildViewModel() }

        val movieAdapter = MovieAdapter(this@MainActivity)
        binding.recyclerviewMovie.adapter = movieAdapter

        viewModel.movies.observe(this@MainActivity, Observer {movies -> movieAdapter.submitList(movies)})

        binding.recyclerviewMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lm = recyclerView.layoutManager as GridLayoutManager
                val lastVisible = lm.findLastVisibleItemPosition()
                Log.i(TAG, lastVisible.toString())
                viewModel.lastVisibleItem.value = lastVisible

            }
        })

        /**
         * Function Shape 2 -> listener : RecyclerView.OnScrollListener
         * Comment block -> binding.recyclerviewMovie.addOnScrollListener...
         */
        //lastVisibleEvents()

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////


    private fun buildViewModel() = MainViewModel(
        MovieRepositoryImpl(
            MovieRoomDataSource(app.roomdb),
            MovieApiDataSource(getString(R.string.api_key_remote_repository))
        )
    )

    private inline fun <reified T : ViewModel> FragmentActivity.getViewModel(crossinline factory : () -> T) : T {
        val vmf = object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = factory() as T
        }
        return ViewModelProvider(this, vmf).get()
    }

    /**
     * Shape 2 -> listener : RecyclerView.OnScrollListener
     */
    private fun lastVisibleEvents() {
        val listener = object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                //Toast.makeText(this@MainActivity, "Scroll", Toast.LENGTH_LONG).show()
                val lm = recyclerView.layoutManager as GridLayoutManager
                Log.i(TAG, lm.findLastVisibleItemPosition().toString())
                val lastVisible = lm.findLastVisibleItemPosition()
                viewModel.lastVisibleItem.value = lastVisible
            }
        }
        binding.recyclerviewMovie.addOnScrollListener(listener)
    }

}

