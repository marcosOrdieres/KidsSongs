package com.mamarene.kidsSongs

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.HorizontalScrollView
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.AdListener

import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import core.onEndScroll
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null

    private var videoViewGallina: VideoView? = null // POR OCHO
    private var videoViewBruja: VideoView? = null // POR OCHO
    private var videoViewRana: VideoView? = null // POR OCHO
    private var videoViewPollo: VideoView? = null // POR OCHO
    private var videoViewBebe: VideoView? = null // POR OCHO
    private var videoViewSol: VideoView? = null // POR OCHO
    private var videoViewCoche: VideoView? = null // POR OCHO
    private var videoViewNana: VideoView? = null // POR OCHO

    private var mediaController: MediaController? = null
    private var uri: Uri? = null
    private var isContinuously = false
    private var brujaAd = false
    private var stopPosition = 0
    private var horizontalScrollViewComp: HorizontalScrollView? = null
    lateinit var adView : AdView
    private lateinit var mInterstitialAd: InterstitialAd


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaPlayer = MediaPlayer.create(this, R.raw.happy)

        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}

        adView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        videoViewGallina = findViewById<VideoView>(R.id.videoViewGallina)
        videoViewBruja = findViewById<VideoView>(R.id.videoViewBruja)
        videoViewRana = findViewById<VideoView>(R.id.videoViewRana)
        videoViewPollo = findViewById<VideoView>(R.id.videoViewPollo)
        videoViewBebe = findViewById<VideoView>(R.id.videoViewBebe)
        videoViewSol = findViewById<VideoView>(R.id.videoViewSol)
        videoViewCoche = findViewById<VideoView>(R.id.videoViewCoche)
        videoViewNana = findViewById<VideoView>(R.id.videoViewNana)

        adView = findViewById(R.id.adView)
        horizontalScrollViewComp = findViewById(R.id.horizontalScrollView)

        mediaController = MediaController(this)
        mediaController?.setAnchorView(videoViewGallina)

        val uriPathGallina = ("android.resource://" + packageName + "/" + R.raw.gallina)
        uri = Uri.parse(uriPathGallina)
        videoViewGallina!!.setVideoURI(uri) // POR OCHO

        val uriPathBruja = ("android.resource://" + packageName + "/" + R.raw.bruja)
        uri = Uri.parse(uriPathBruja)
        videoViewBruja!!.setVideoURI(uri) // POR OCHO

        val uriPathRana = ("android.resource://" + packageName + "/" + R.raw.rana)
        uri = Uri.parse(uriPathRana)
        videoViewRana!!.setVideoURI(uri) // POR OCHO

        val uriPathPollo = ("android.resource://" + packageName + "/" + R.raw.pollitos_dicen)
        uri = Uri.parse(uriPathPollo)
        videoViewPollo!!.setVideoURI(uri) // POR OCHO

        val uriPathBebe = ("android.resource://" + packageName + "/" + R.raw.nana_bebes)
        uri = Uri.parse(uriPathBebe)
        videoViewBebe!!.setVideoURI(uri) // POR OCHO

        val uriPathSol = ("android.resource://" + packageName + "/" + R.raw.sol)
        uri = Uri.parse(uriPathSol)
        videoViewSol!!.setVideoURI(uri) // POR OCHO

        val uriPathCoche = ("android.resource://" + packageName + "/" + R.raw.coche)
        uri = Uri.parse(uriPathCoche)
        videoViewCoche!!.setVideoURI(uri) // POR OCHO

        val uriPathNana = ("android.resource://" + packageName + "/" + R.raw.dormir)
        uri = Uri.parse(uriPathNana)
        videoViewNana!!.setVideoURI(uri) // POR OCHO


        mediaPlayer?.setOnPreparedListener{
            mediaPlayer?.start()
        }

        if(!videoViewGallina!!.isPlaying){
            videoViewGallina!!.visibility = View.INVISIBLE
        } // POR OCHO

        if(!videoViewBruja!!.isPlaying){
            videoViewBruja!!.visibility = View.INVISIBLE
        } // POR OCHO
        if(!videoViewRana!!.isPlaying){
            videoViewRana!!.visibility = View.INVISIBLE
        } // POR OCHO
        if(!videoViewPollo!!.isPlaying){
            videoViewPollo!!.visibility = View.INVISIBLE
        } // POR OCHO
        if(!videoViewBebe!!.isPlaying){
            videoViewBebe!!.visibility = View.INVISIBLE
        } // POR OCHO
        if(!videoViewSol!!.isPlaying){
            videoViewSol!!.visibility = View.INVISIBLE
        } // POR OCHO
        if(!videoViewCoche!!.isPlaying){
            videoViewCoche!!.visibility = View.INVISIBLE
        } // POR OCHO
        if(!videoViewNana!!.isPlaying){
            videoViewNana!!.visibility = View.INVISIBLE
        } // POR OCHO


        arrowLeft?.visibility = GONE

        horizontalScrollView.onEndScroll({
            arrowRight.visibility = VISIBLE
            arrowLeft?.visibility = GONE
        }) {
            arrowRight.visibility = GONE
            arrowLeft.visibility = VISIBLE
        }
        goBack?.visibility = GONE


        imageButtonFirst.setOnClickListener{
                mediaPlayer?.stop()
                videoViewGallina!!.requestFocus()
                videoViewGallina!!.setMediaController(mediaController)
                if(videoViewGallina!!.currentPosition > 0){
                    videoViewGallina!!.seekTo(stopPosition)
                    videoViewGallina!!.start()
                    horizontalScrollViewComp?.visibility = GONE
                } else{
                    videoViewGallina!!.visibility = VISIBLE
                    arrowRight?.visibility = GONE
                    arrowLeft?.visibility = GONE
                    horizontalScrollViewComp?.visibility = GONE
                    adView?.visibility = GONE
                    videoViewGallina!!.start()
                    goBack?.visibility = VISIBLE
                }
        }

        imageButtonSecond.setOnClickListener{
                mediaPlayer?.stop()
                videoViewBruja!!.requestFocus()
                videoViewBruja!!.setMediaController(mediaController)

                if(videoViewBruja!!.currentPosition > 0){
                    videoViewBruja!!.seekTo(stopPosition)
                    videoViewBruja!!.start()
                    horizontalScrollViewComp?.visibility = GONE
                } else{
                    mInterstitialAd.show()
                    brujaAd = true
                }
        }

        imageButtonThird.setOnClickListener{

                mediaPlayer?.stop()
                videoViewRana!!.requestFocus()
                videoViewRana!!.setMediaController(mediaController)
                if(videoViewRana!!.currentPosition > 0){
                    videoViewRana!!.seekTo(stopPosition)
                    videoViewRana!!.start()
                    horizontalScrollViewComp?.visibility = GONE
                } else{
                    videoViewRana!!.visibility = VISIBLE
                    arrowRight?.visibility = GONE
                    arrowLeft?.visibility = GONE
                    horizontalScrollViewComp?.visibility = GONE
                    adView?.visibility = GONE
                    videoViewRana!!.start()
                    goBack?.visibility = VISIBLE
                }
        }

        imageButtonFourth.setOnClickListener{

                mediaPlayer?.stop()
                videoViewPollo!!.requestFocus()
                videoViewPollo!!.setMediaController(mediaController)
                if(videoViewPollo!!.currentPosition > 0){
                    videoViewPollo!!.seekTo(stopPosition)
                    videoViewPollo!!.start()
                    horizontalScrollViewComp?.visibility = GONE
                } else{
                    videoViewPollo!!.visibility = VISIBLE
                    arrowRight?.visibility = GONE
                    arrowLeft?.visibility = GONE
                    horizontalScrollViewComp?.visibility = GONE
                    adView?.visibility = GONE
                    videoViewPollo!!.start()
                    goBack?.visibility = VISIBLE
                }
        }

        imageButtonFifth.setOnClickListener{
                mediaPlayer?.stop()
                videoViewBebe!!.requestFocus()
                videoViewBebe!!.setMediaController(mediaController)
                if(videoViewBebe!!.currentPosition > 0){
                    videoViewBebe!!.seekTo(stopPosition)
                    videoViewBebe!!.start()
                    horizontalScrollViewComp?.visibility = GONE
                } else{
                    videoViewBebe!!.visibility = VISIBLE
                    arrowRight?.visibility = GONE
                    arrowLeft?.visibility = GONE
                    horizontalScrollViewComp?.visibility = GONE
                    adView?.visibility = GONE
                    videoViewBebe!!.start()
                    goBack?.visibility = VISIBLE
                }
        }

        imageButtonSix.setOnClickListener{
                mediaPlayer?.stop()
                videoViewSol!!.requestFocus()
                videoViewSol!!.setMediaController(mediaController)
                if(videoViewSol!!.currentPosition > 0){
                    videoViewSol!!.seekTo(stopPosition)
                    videoViewSol!!.start()
                    horizontalScrollViewComp?.visibility = GONE
                } else{
                    mInterstitialAd.show()
                }
        }

        imageButtonSeven.setOnClickListener{
                mediaPlayer?.stop()
                videoViewCoche!!.requestFocus()
                videoViewCoche!!.setMediaController(mediaController)
                if(videoViewCoche!!.currentPosition > 0){
                    videoViewCoche!!.seekTo(stopPosition)
                    videoViewCoche!!.start()
                    horizontalScrollViewComp?.visibility = GONE
                } else{
                    videoViewCoche!!.visibility = VISIBLE
                    arrowRight?.visibility = GONE
                    arrowLeft?.visibility = GONE
                    horizontalScrollViewComp?.visibility = GONE
                    adView?.visibility = GONE
                    videoViewCoche!!.start()
                    goBack?.visibility = VISIBLE
                }
        }

        imageButtonEight.setOnClickListener{
                mediaPlayer?.stop()
                videoViewNana!!.requestFocus()
                videoViewNana!!.setMediaController(mediaController)
                if(videoViewNana!!.currentPosition > 0){
                    videoViewNana!!.seekTo(stopPosition)
                    videoViewNana!!.start()
                    horizontalScrollViewComp?.visibility = GONE
                } else{
                    videoViewNana!!.visibility = VISIBLE
                    arrowRight?.visibility = GONE
                    arrowLeft?.visibility = GONE
                    horizontalScrollViewComp?.visibility = GONE
                    adView?.visibility = GONE
                    videoViewNana!!.start()
                    goBack?.visibility = VISIBLE
                }
        }


        mInterstitialAd.setAdListener(object : AdListener() {
            override fun onAdClosed() {
                // reschedule

                if(brujaAd == true){
                    videoViewBruja!!.visibility = VISIBLE
                    arrowRight?.visibility = GONE
                    arrowLeft?.visibility = GONE
                    horizontalScrollViewComp?.visibility = GONE
                    adView?.visibility = GONE
                    videoViewBruja!!.start()
                    goBack?.visibility = VISIBLE
                    brujaAd = false
                    mInterstitialAd.loadAd(AdRequest.Builder().build())
                } else{
                    videoViewSol!!.visibility = VISIBLE
                    arrowRight?.visibility = GONE
                    arrowLeft?.visibility = GONE
                    horizontalScrollViewComp?.visibility = GONE
                    adView?.visibility = GONE
                    videoViewSol!!.start()
                    goBack?.visibility = VISIBLE
                    mInterstitialAd.loadAd(AdRequest.Builder().build())
                }

            }
        })


        goBack.setOnClickListener{
            mediaPlayer?.prepare()
            mediaPlayer?.start()
            arrowRight?.visibility = VISIBLE
            horizontalScrollViewComp?.visibility = VISIBLE

            videoViewGallina?.visibility = GONE // POR OCHO
            videoViewBruja?.visibility = GONE // POR OCHO
            videoViewRana?.visibility = GONE // POR OCHO
            videoViewPollo?.visibility = GONE // POR OCHO
            videoViewNana?.visibility = GONE // POR OCHO
            videoViewSol?.visibility = GONE // POR OCHO
            videoViewCoche?.visibility = GONE // POR OCHO
            videoViewBebe?.visibility = GONE // POR OCHO

            goBack?.visibility = GONE
        }

        arrowRight.setOnClickListener{
            val hola = horizontalScrollViewComp?.getScrollX()
            val we = hola?.plus(1200)
            val nonNullableInt: Int = we!!
            horizontalScrollViewComp?.scrollTo(nonNullableInt, 0)
        }
        arrowLeft.setOnClickListener{
            val hola = horizontalScrollViewComp?.getScrollX()
            val nonNullableInt: Int = hola!!
            horizontalScrollViewComp?.scrollTo(-nonNullableInt, 0)
        }
    }
}
