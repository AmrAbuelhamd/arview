package com.blogspot.soyamr.arview

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blogspot.soyamr.arview.model.ArviewRepository
import com.blogspot.soyamr.arview.model.Result
import com.blogspot.soyamr.arview.model.domain.FinalGameInfo
import kotlinx.coroutines.launch


class MainActivityViewModel @ViewModelInject constructor(private val repository: ArviewRepository) :
    ViewModel() {
    private val _games: MutableLiveData<List<FinalGameInfo>> = MutableLiveData(ArrayList())
    val games: LiveData<List<FinalGameInfo>> = _games

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading


    init {
        getGamesInfo()
    }

    private fun getGamesInfo() {
        viewModelScope.launch() {
            _isLoading.value = true
            _games.value = (repository.getGames() as Result.Success).data
            _isLoading.value = false
        }
    }


}