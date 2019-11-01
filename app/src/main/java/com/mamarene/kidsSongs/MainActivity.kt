package com.mamarene.kidsSongs

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.HorizontalScrollView
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import core.onEndScroll
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private var videoView: VideoView? = null
    private var mediaController: MediaController? = null
    private var uri: Uri? = null
    private var isContinuously = false
    private var stopPosition = 0
    private var horizontalScrollViewComp: HorizontalScrollView? = null
    lateinit var adView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaPlayer = MediaPlayer.create(this, R.raw.happy)

        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}
        adView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        videoView = findViewById<VideoView>(R.id.videoView)
        adView = findViewById(R.id.adView)

        horizontalScrollViewComp = findViewById(R.id.horizontalScrollView)

        mediaController = MediaController(this)
        mediaController?.setAnchorView(videoView)

        val uriPath = ("android.resource://" + packageName + "/" + R.raw.shark)
        uri = Uri.parse(uriPath)
        videoView!!.setVideoURI(uri)


        mediaPlayer?.setOnPreparedListener{
            mediaPlayer?.start()
        }

        if(!videoView!!.isPlaying){
            videoView!!.visibility = View.INVISIBLE
        }
        arrowLeft?.visibility = GONE


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
                    horizontalScrollViewComp!!.removeAllViews()

                } else{
                    videoView!!.visibility = VISIBLE
                    arrowRight?.visibility = GONE
                    arrowLeft?.visibility = GONE
                    horizontalScrollViewComp!!.removeAllViews()
                    videoView!!.start()
                }
            } else{
                isContinuously = false
                videoView!!.pause()
                stopPosition = videoView!!.currentPosition
            }
        }
    }
}
