package com.example.apnamusic

import android.annotation.SuppressLint
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
    lateinit var mediaPlayer : MediaPlayer
//    private lateinit var btnPlay:Button
//    private lateinit var btnPause:Button
//    private lateinit var btnStop:Button
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
  myRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
       supportActionBar?.hide()
        var imageArray = arrayOf(
            R.drawable.image1,
            R.drawable.secondimage,
            R.drawable.thirdimage,
            R.drawable.fourthimage,
            R.drawable.fifthimage
        )



        var titleArray = arrayOf("Woh lamhe" , "teri Jhuki Nazar" , "Mockingbird" , "Kesariya" ,"Imagination")

        var singerArray = arrayOf("by Atif Aslam" , "by Pritam" , "by Eminem" , "by Arijit" , "by Shawn")
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
               var intent = Intent(this@HomeActivity , SongActivity::class.java)
                intent.putExtra("position" , position)
                intent.putExtra("title" ,titleArray[position])
                intent.putExtra("imageId" ,imageArray[position])
                intent.putExtra("singerName" , singerArray[position])

                startActivityForResult(intent, 1)
                mediaPlayer.stop()

            }

        })

    }

override fun onBackPressed() {
    super.onBackPressed()
    finish()
}
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if(requestCode == 1 && resultCode == RESULT_OK) {
//            // Here you can handle the result of the SongActivity, for example:
//            // if the user has clicked the back button in the SongActivity, you can
//            // do something here, like displaying a Toast or navigating to the HomeActivity
//            // without showing the splash screen.
//
//            // You can navigate to HomeActivity without showing the splash screen using:
//            // val intent = Intent(this, HomeActivity::class.java)
//            // startActivity(intent)
//            // finish()
//             val intent = Intent(this, HomeActivity::class.java)
//             startActivity(intent)
//             finish()
//        }
//    }


}