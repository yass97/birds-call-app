package com.yass.birdcallsapp

import androidx.annotation.RawRes
import androidx.annotation.StringRes

enum class BirdNameAndVoice(@StringRes val birdName: Int, @RawRes val voice: Int) {
    SUZUME(R.string.suzume, R.raw.suzume),
    OORURI(R.string.ooruri, R.raw.ooruri),
    UGUISU(R.string.uguisu, R.raw.uguisu),
    SANKOTYOU(R.string.sankotyou, R.raw.sankotyou),
    UMINEKO(R.string.umineko, R.raw.umineko),
    AKAHARA(R.string.akahara, R.raw.akahara),
    KIBITAKI(R.string.kibitaki, R.raw.kibitaki),
    MOSUKEMISOSAZAI(R.string.mosukemisosazai, R.raw.mosukemisosazai),
    YATYOU_GUNDAN(R.string.yatyou_gundan, R.raw.yatyou_gundan),
    KAMO_GUNDAN(R.string.kamo_gundan, R.raw.kamo_gundan)
}