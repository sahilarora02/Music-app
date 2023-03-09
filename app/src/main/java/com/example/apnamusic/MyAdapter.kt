package com.example.apnamusic

import android.app.Activity
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(var songArrayList: ArrayList<Song> , var context:Activity):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
private lateinit var myListener:onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
fun setItemClickListener(listener: onItemClickListener){
    myListener = listener
}



    // create new view instance when layoutmanager fails to find a suitable View for each time
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.each_song , parent ,false)
        return MyViewHolder(itemView , myListener )

    }
    // Populates items with data
    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val currentItem = songArrayList[position]
//        holder.hTitle.text = currentItem.newsHeading
//        holder.hImage.setImageResource(currentItem.newsImage)
        holder.image.setImageResource(currentItem.imageId)
        holder.title.text = currentItem.title
        holder.singer.text = currentItem.singer
    }
    // how many list items are present in your array
    override fun getItemCount(): Int {
        return songArrayList.size
    }

    //it holds the view ,so views are not created every time to save memory
    class MyViewHolder(itemView: View , Listener:onItemClickListener) :RecyclerView.ViewHolder(itemView) {
//        val hTitle = itemView.findViewById<TextView>(R.id.headingTitle)
//        val hImage = itemView.findViewById<ShapeableImageView>(R.id.headingImage)
        var image = itemView.findViewById<ShapeableImageView>(R.id.Image)
        var title = itemView.findViewById<TextView>(R.id.Title)
        var singer = itemView.findViewById<TextView>(R.id.Singer)
        init {
            itemView.setOnClickListener {
                Listener.onItemClick(adapterPosition)
            }
        }

    }

}