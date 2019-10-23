package com.mamarene.kidsSongs

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaPlayer = MediaPlayer.create(this, R.raw.happy)

        setContentView(R.layout.activity_main)

        mediaPlayer?.setOnPreparedListener{
            mediaPlayer?.start()
            println("holaaaaaa")
        }

        imageButtonFirst.setOnClickListener{
            println("weeee")
        }
    }
}
