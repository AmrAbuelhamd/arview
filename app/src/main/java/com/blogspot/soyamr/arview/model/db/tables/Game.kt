package com.blogspot.soyamr.arview.model.db.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blogspot.soyamr.arview.model.domain.FinalGameInfo

@Entity
data class Game(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val name: String,
    val coverUrl: String,
    val viewersNum: Int,
    val channelsNum: Int,
) {
    fun toDomain() = FinalGameInfo(name, coverUrl, viewersNum, channelsNum)
}