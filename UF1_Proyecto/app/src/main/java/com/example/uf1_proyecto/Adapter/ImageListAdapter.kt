package com.example.uf1_proyecto.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.example.uf1_proyecto.R



class ImageListAdapter( var images: List<String>) :
    RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_detail_images, parent, false)
        context = parent.context
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(images[position]).into(holder.pic)
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pic:ImageView
        init {
            pic = itemView.findViewById(R.id.ItemImagenes)
        }
    }
}