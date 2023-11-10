package com.example.uf1_proyecto.Domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PokeItemK {
    @SerializedName("pokemon")
    @Expose
    var pokemon: List<PokemonK>? = null
}
