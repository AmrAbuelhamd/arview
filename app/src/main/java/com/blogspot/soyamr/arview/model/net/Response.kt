package com.blogspot.soyamr.arview.model.net

import kotlinx.serialization.Serializable


@Serializable
data class Response(val top: List<GameInfo>)

@Serializable
data class GameInfo(
    val game: Game,
    val viewers: Int,
    val channels: Int
)

@Serializable
data class Game(
    val name: String,
    val box: Box,
)

@Serializable
data class Box(
    val medium: String,
)