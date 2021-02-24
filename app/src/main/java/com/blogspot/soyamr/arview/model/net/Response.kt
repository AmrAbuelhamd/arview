package com.blogspot.soyamr.arview.model.net

import com.blogspot.soyamr.arview.model.domain.FinalGameInfo
import kotlinx.serialization.Serializable


@Serializable
data class Response(val top: List<GameInfo>)

@Serializable
data class GameInfo(
    val game: Game,
    val viewers: Int,
    val channels: Int
) {
    fun toDBTable() = com.blogspot.soyamr.arview.model.db.tables.Game(
        name = game.name,
        coverUrl = game.box.medium,
        viewersNum = viewers,
        channelsNum = channels
    )
    fun toDomain() = FinalGameInfo(
        game.name,
        game.box.medium,
        viewers,
        channels
    )
}

@Serializable
data class Game(
    val name: String,
    val box: Box,
)

@Serializable
data class Box(
    val medium: String,
)