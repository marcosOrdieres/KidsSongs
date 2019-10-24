package com.mamarene.kidsSongs

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.VideoView
import android.widget.MediaController
import android.net.Uri
import android.widget.ProgressBar



class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    private var videoView: VideoView? = null
    private var mediaController: MediaController? = null
    private var uri: Uri? = null
    private var isContinuously = false
    private var stopPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaPlayer = MediaPlayer.create(this, R.raw.happy)

        setContentView(R.layout.activity_main)

        videoView = findViewById(R.id.videoView) as VideoView


        mediaController = MediaController(this)
        mediaController?.setAnchorView(videoView)

        val uriPath = ("android.resource://" + packageName + "/" + R.raw.shark)
        uri = Uri.parse(uriPath)
        videoView!!.setVideoURI(uri)


        mediaPlayer?.setOnPreparedListener{
            mediaPlayer?.start()
        }


        imageButtonFirst.setOnClickListener{
            if (!isContinuously) {
                isContinuously = true
                mediaPlayer?.pause()
                videoView!!.requestFocus()
                videoView!!.setMediaController(mediaController)
                if(videoView!!.getCurrentPosition() > 0){
                    videoView!!.seekTo(stopPosition)
                    videoView!!.start()

                } else{
                    videoView!!.start()
                }

            } else{
                isContinuously = false
                videoView!!.pause()
                stopPosition = videoView!!.getCurrentPosition(); //stopPosition is an int
                println(videoView!!.getCurrentPosition())

            }
        }
    }
}
