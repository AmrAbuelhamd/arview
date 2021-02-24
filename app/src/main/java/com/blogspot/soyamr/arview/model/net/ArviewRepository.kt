package com.blogspot.soyamr.arview.model.net

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.blogspot.soyamr.notforgotagain.model.net.Network
import com.bumptech.glide.Glide.with
import com.squareup.picasso.Picasso
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArviewRepository @Inject constructor(@ApplicationContext val context: Context) {

    private val apiService: TaskApiService

    init {
        apiService = Network.retrofit
    }

    suspend fun getGames() =
        withContext(Dispatchers.IO) { apiService.getGames() }
}