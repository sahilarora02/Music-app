package com.example.apnamusic

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException

class HomeActivity : AppCompatActivity() {
    lateinit var myRecyclerView: RecyclerView
    lateinit var songArrayList: ArrayList<Song>
//    private lateinit var btnPlay:Button
//    private lateinit var btnPause:Button
//    private lateinit var btnStop:Button
    lateinit var mediaPlayer : MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
  myRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
       supportActionBar?.hide()
        var imageArray = arrayOf(
            R.drawable.image,
            R.drawable.secondimage,
            R.drawable.thirdimage,
            R.drawable.fourthimage,
            R.drawable.fifthimage
        )

         var songArray = arrayOf(R.raw.music,
             R.raw.music2,
             R.raw.music3,
             R.raw.music4,
             R.raw.music,

         )

        var titleArray = arrayOf("Dil Ibadat" , "teri Jhuki Nazar" , "Mockingbird" , "Kesariya" ,"Imagination")

        var singerArray = arrayOf("by K.K." , "by Pritam" , "by Eminem" , "by Arijit" , "by Shawn")
myRecyclerView.layoutManager = LinearLayoutManager(this)
        songArrayList = ArrayList<Song>()
        for(index in imageArray.indices){
            var songItem = Song(imageArray[index] , titleArray[index] , singerArray[index])
            songArrayList.add(songItem)
        }

        var adapter = MyAdapter(songArrayList , this)
        myRecyclerView.adapter = adapter

//    btnPlay = findViewById(R.id.Play)
//        btnPause = findViewById(R.id.Pause)
//        btnStop = findViewById(R.id.Stop)
//        btnPlay.setOnClickListener {
//            playAudio()
//        }
//        btnPause.setOnClickListener {
//            pauseAudio()
//        }
//        btnStop.setOnClickListener {
//            stopAudio()
//        }
        adapter.setItemClickListener(object : MyAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                // on clicking each Item, what action u want to perform
                mediaPlayer = MediaPlayer.create(applicationContext, songArray[position])
                mediaPlayer.start()

            }

        })

    }

//    private fun playAudio() {
//
//        mediaPlayer?.start()
////        val audioUrl ="https://www.bensound.com/bensound-music/bensound-ukulele.mp3"
////        mediaPlayer = MediaPlayer()
////        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
////        try {
////           mediaPlayer!!.setDataSource(audioUrl)
////            mediaPlayer!!.prepare()
////            mediaPlayer!!.start()
////        }catch (e:IOException){
////            e.printStackTrace()
////        }
//        Toast.makeText(this, "Song Started", Toast.LENGTH_SHORT).show()
//    }
//
//    private fun pauseAudio() {
//
//        mediaPlayer?.pause()
//    }
//    private fun stopAudio(){
//        mediaPlayer?.stop()
//    }


}