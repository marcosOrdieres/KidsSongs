package com.mamarene.kidsSongs

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer = MediaPlayer.create(this, R.raw.happy)
        mediaPlayer?.setOnPreparedListener{
            //mediaPlayer?.start()
            println("readyyyyy")
        }
    }
}
