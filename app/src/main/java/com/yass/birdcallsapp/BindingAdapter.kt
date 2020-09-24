package com.yass.birdcallsapp

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapter {

    @BindingAdapter("setBirdName")
    @JvmStatic
    fun setBirdName(textView: TextView, bird: Bird?) {

        bird ?: return

        textView.text = bird.name
    }

    @BindingAdapter("switchPlayState")
    @JvmStatic
    fun switchPlayState(textView: TextView, bird: Bird?) {

        bird ?: return

        textView.text = bird.playState.name

        when (bird.playState) {
            PlayStatus.ON -> textView.setTextColor(Color.BLUE)
            PlayStatus.OFF -> textView.setTextColor(Color.GRAY)
        }
    }
}