package com.blogspot.soyamr.notforgotagain.model.db.tables

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.blogspot.soyamr.arview.model.db.tables.Game

@Dao
interface GameDao {
    @Query("SELECT * FROM Game")
    fun getAll(): List<Game>

    @Insert
    fun insertGames(games: List<Game>)

    @Query("DELETE FROM game")
    fun deleteAll()
}