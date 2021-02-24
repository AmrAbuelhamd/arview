package com.blogspot.soyamr.arview.model.net

import retrofit2.http.GET

interface TaskApiService {

    @GET("games/top/")
    suspend fun getGames(): Response


}