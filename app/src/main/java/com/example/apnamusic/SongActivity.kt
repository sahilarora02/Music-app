package com.example.apnamusic

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_song.*
import java.text.FieldPosition

class SongActivity : AppCompatActivity() {
    lateinit var mediaPlayer : MediaPlayer
    lateinit var title:TextView
    lateinit var Image:ImageView
    var songArray = arrayOf(R.raw.music5
        ,
        R.raw.music3,
        R.raw.music4,

        R.raw.music,

        R.raw.music2,

        )
    var imageArray = arrayOf(
        R.drawable.image1,
        R.drawable.secondimage,
        R.drawable.thirdimage,
        R.drawable.fourthimage,
        R.drawable.fifthimage
    )



    var titleArray = arrayOf("Woh lamhe" , "teri Jhuki Nazar" , "Mockingbird" , "Kesariya" ,"Imagination")

    var singerArray = arrayOf("by Atif Aslam " , "by Pritam" , "by Eminem" , "by Arijit" , "by Shawn")
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)
        supportActionBar?.hide()


        var Play = findViewById<ImageView>(R.id.Play)
        var Previous = findViewById<ImageView>(R.id.Previous)
        var Next = findViewById<ImageView>(R.id.Next)
        var singerName = findViewById<TextView>(R.id.singerName)
         Image = findViewById<ImageView>(R.id.songImage)
         title = findViewById<TextView>(R.id.imageName)

        var heading = intent.getStringExtra("title")
        var singer = intent.getStringExtra("singerName")
        var position = intent.getIntExtra("position" , 0)
        Log.i("position=>" , position.toString())
        var imageId = intent.getIntExtra("imageId" ,R.drawable.fourthimage)

        title.text = heading
        singerName.text = singer
        Image.setImageResource(imageId)
   songPlaying(position,applicationContext)




        Previous.setOnClickListener {
            if(mediaPlayer!!.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.release()
            }else{
                mediaPlayer.release()
            }
                if (position != 0) {
                    position = position - 1
                } else {
                    position = 4
                }
            title.text = titleArray[position]
            singerName.text = singerArray[position]
            Image.setImageResource(imageArray[position])
            Play.setImageResource(R.drawable.ic_baseline_play_circle_filled_24)
            Handler().postDelayed({
                songPlaying(position , applicationContext)

            },500)
        }
        Next.setOnClickListener{



                if (mediaPlayer!!.isPlaying) {
                    mediaPlayer.stop()
                    mediaPlayer.release()
                } else {
                    mediaPlayer.release()
                }
                if (position != 4) {
                    position = position + 1
                } else {
                    position = 0
                }
                title.text = titleArray[position]
                singerName.text = singerArray[position]
                Image.setImageResource(imageArray[position])
                Play.setImageResource(R.drawable.ic_baseline_play_circle_filled_24)
                Handler().postDelayed({
                    songPlaying(position, applicationContext)

                }, 500)
            }




        Play.setOnClickListener{

            if(mediaPlayer!!.isPlaying){
                mediaPlayer.pause()
                Play.setImageResource(R.drawable.ic_baseline_play_circle_filled_24)

            }else{
                mediaPlayer.start()
                Play.setImageResource(R.drawable.ic_baseline_pause_circle_24)
            }


        }
    }

    override fun onBackPressed() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }
    override fun onDestroy() {
        mediaPlayer?.release()
//        mediaPlayer = null
        super.onDestroy()
    }
    fun songPlaying(position: Int, context: Context) {
        var currentPosition = position // declare a new variable and initialize it with the position parameter
        mediaPlayer = MediaPlayer.create(context, songArray[currentPosition])
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            // Loop the song
            if (mediaPlayer!!.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.release()
            } else {
                mediaPlayer.release()
            }
            if (currentPosition != 4) {
                currentPosition = currentPosition + 1 // reassign the new variable
            } else {
                currentPosition = 0 // reassign the new variable
            }
            title.text = titleArray[currentPosition]
            singerName.text = singerArray[currentPosition]
            Image.setImageResource(imageArray[currentPosition])
            Play.setImageResource(R.drawable.ic_baseline_play_circle_filled_24)
                songPlaying(currentPosition, applicationContext)

        }
        Play.setImageResource(R.drawable.ic_baseline_pause_circle_24)
    }

}