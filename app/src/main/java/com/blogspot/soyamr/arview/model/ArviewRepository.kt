package com.blogspot.soyamr.arview.model

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.blogspot.soyamr.arview.model.db.ArviewDataBase
import com.blogspot.soyamr.arview.model.domain.FinalGameInfo
import com.blogspot.soyamr.arview.model.net.TaskApiService
import com.blogspot.soyamr.notforgotagain.model.db.tables.GameDao
import com.blogspot.soyamr.notforgotagain.model.net.Network
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

@Singleton
class ArviewRepository @Inject constructor(@ApplicationContext val context: Context) {

    private val apiService: TaskApiService = Network.retrofit
    private val gameDao: GameDao

    init {
        val db = ArviewDataBase.getDatabase(context)
        gameDao = db.categoryDao()
    }

    suspend fun getGames(): Result<List<FinalGameInfo>> =
        withContext(Dispatchers.IO) {
            if (isOnline()) {
                val games = apiService.getGames()
                cleanDataBase()
                gameDao.insertGames(games.top.map { it.toDBTable() })
                return@withContext Result.Success(games.top.map { it.toDomain() })
            } else {
                return@withContext Result.Success(gameDao.getAll().map { it.toDomain() })
            }
        }


    private fun isOnline(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
//                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
//                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
//                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
//        }
        return false
    }


    private fun cleanDataBase() {
        gameDao.deleteAll()
    }
}