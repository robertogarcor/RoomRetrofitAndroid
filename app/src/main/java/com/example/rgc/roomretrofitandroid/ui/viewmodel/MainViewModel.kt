package com.example.rgc.roomretrofitandroid.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.rgc.roomretrofitandroid.data.domain.model.Movie
import com.example.rgc.roomretrofitandroid.data.domain.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository : MovieRepositoryImpl) : ViewModel() {

    private val TAG = "MainViewModel_lastVisible"

    val movies : LiveData<List<Movie>> get() = repository.getMovies()

    val lastVisibleItem = MutableLiveData<Int>().apply { value = 0 }

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                lastVisibleItem.asFlow().collect { notifyLastVisible(it) }
            }
        }
    }

    private suspend fun notifyLastVisible(lastVisible: Int) {
        Log.d(TAG, " : $lastVisible")
        repository.requiredNewData(lastVisible)
    }
}