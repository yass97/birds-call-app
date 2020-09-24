package com.yass.birdcallsapp

data class Bird(
    val name: String,
    val voice: Int,
    var playState: PlayStatus
)