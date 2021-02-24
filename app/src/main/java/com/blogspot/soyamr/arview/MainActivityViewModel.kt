package com.blogspot.soyamr.arview

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blogspot.soyamr.arview.model.net.ArviewRepository
import com.blogspot.soyamr.arview.model.net.FinalGameInfo
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import kotlinx.coroutines.launch


class MainActivityViewModel @ViewModelInject constructor(private val repository: ArviewRepository) :
    ViewModel() {
    private val _games: MutableLiveData<List<FinalGameInfo>> = MutableLiveData(ArrayList())
    val games: LiveData<List<FinalGameInfo>> = _games


    init {
        getGamesInfo()
    }

    private fun getGamesInfo() {
        viewModelScope.launch {
            _games.value = repository.getGames().top.map {
                FinalGameInfo(
                    it.game.name,
                    it.game.box.medium,
                    it.viewers,
                    it.channels
                )
            }
        }
    }


}