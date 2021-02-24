package com.blogspot.soyamr.arview.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.blogspot.soyamr.arview.model.db.tables.Game
import com.blogspot.soyamr.notforgotagain.model.db.tables.*


@Database(
    entities = [Game::class],
    version = 1
)
abstract class ArviewDataBase : RoomDatabase() {

    abstract fun categoryDao(): GameDao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.

        @Volatile
        private var INSTANCE: ArviewDataBase? = null
        fun getDatabase(context: Context): ArviewDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArviewDataBase::class.java,
                    "arview_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

    }

}