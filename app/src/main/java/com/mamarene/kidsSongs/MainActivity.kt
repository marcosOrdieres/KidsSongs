package com.mamarene.kidsSongs

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.MediaController
import core.onEndScroll

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    private var mediaController: MediaController? = null
    private var uri: Uri? = null
    private var isContinuously = false
    private var stopPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaPlayer = MediaPlayer.create(this, R.raw.happy)

        setContentView(R.layout.activity_main)

        mediaController = MediaController(this)
        mediaController?.setAnchorView(videoView)

        val uriPath = ("android.resource://" + packageName + "/" + R.raw.shark)
        uri = Uri.parse(uriPath)
        videoView!!.setVideoURI(uri)
        arrowLeft?.visibility = GONE

        mediaPlayer?.setOnPreparedListener{
            mediaPlayer?.start()
        }

        horizontalScrollView.onEndScroll({
            arrowRight.visibility = VISIBLE
            arrowLeft?.visibility = GONE
        }) {
            arrowRight.visibility = GONE
            arrowLeft.visibility = VISIBLE

        }


        imageButtonFirst.setOnClickListener{
            if (!isContinuously) {
                isContinuously = true
                mediaPlayer?.pause()
                videoView!!.requestFocus()
                videoView!!.setMediaController(mediaController)
                if(videoView!!.currentPosition > 0){
                    videoView!!.seekTo(stopPosition)
                    videoView!!.start()
                    //horizontalScrollViewComp

                } else{
                    videoView!!.start()
                }

            } else{
                isContinuously = false
                videoView!!.pause()
                stopPosition = videoView!!.currentPosition //stopPosition is an int
                println(videoView!!.currentPosition)

            }
        }
    }
}
