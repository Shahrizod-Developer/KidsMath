package uz.gita.kidsmath.data.model

import android.content.Context
import android.media.MediaPlayer
import uz.gita.kidsmath.R

object Correct {

    var mediaPlayer = MediaPlayer()

    fun create(context: Context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.correct)
    }
}