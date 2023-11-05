package com.example.uf1_proyecto.Adapter

import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uf1_proyecto.Domain.*
import com.example.uf1_proyecto.*
import com.example.uf1_proyecto.Activity.*

class FilmListAdapter(var items: ListFilm) :
    RecyclerView.Adapter<FilmListAdapter.ViewHolder>() {
    lateinit var context: Context

    class ViewHolder : RecyclerView.ViewHolder {
        var title:TextView
        var score:TextView
        var pic:ImageView
        constructor(itemView: View) : super(itemView) {
            title = itemView.findViewById(R.id.titleText)
            score = itemView.findViewById(R.id.scoretxt)
            pic = itemView.findViewById(R.id.moviepicsmall)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmListAdapter.ViewHolder {
        var inflate = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_film,parent,false)
        context = parent.context
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: FilmListAdapter.ViewHolder, position: Int) {
        holder.title.text = items.data[position].title
        holder.score.text = items.data[position].imdbRating

        Glide.with(holder.itemView.context).load(items.data[position].poster).into(holder.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("id", items.data[position].id)
            holder.itemView.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return items.data.size
    }

}
