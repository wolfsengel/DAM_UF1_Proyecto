package com.example.uf1_proyecto.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uf1_proyecto.Domain.PokeItemK
import com.example.uf1_proyecto.R

class PokemonListAdapter(internal var context: Context, internal var pokemonList: PokeItemK)
    : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var pokemonName: TextView
            var pokemonImage: ImageView
            var pokemonId: TextView
            init {
                pokemonName = itemView.findViewById(R.id.pokemon_name) as TextView
                pokemonImage = itemView.findViewById(R.id.pokemon_image) as ImageView
                pokemonId = itemView.findViewById(R.id.pokemon_id) as TextView
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_viewholder, parent, false)
        context = parent.context
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return pokemonList.pokemon!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(pokemonList.pokemon!![position].img).into(holder.pokemonImage)
        holder.pokemonName.text = pokemonList.pokemon!![position].name
        holder.pokemonId.text = pokemonList.pokemon!![position].id.toString()
    }
}