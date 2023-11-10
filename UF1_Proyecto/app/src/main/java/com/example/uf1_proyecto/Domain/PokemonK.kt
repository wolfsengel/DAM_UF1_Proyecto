package com.example.uf1_proyecto.Domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PokemonK {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("num")
    @Expose
    var num: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("img")
    @Expose
    var img: String? = null

    @SerializedName("type")
    @Expose
    var type: List<String>? = null

    @SerializedName("height")
    @Expose
    var height: String? = null

    @SerializedName("weight")
    @Expose
    var weight: String? = null

    @SerializedName("candy")
    @Expose
    var candy: String? = null

    @SerializedName("candy_count")
    @Expose
    var candyCount: Int? = null

    @SerializedName("egg")
    @Expose
    var egg: String? = null

    @SerializedName("spawn_chance")
    @Expose
    var spawnChance: Double? = null

    @SerializedName("avg_spawns")
    @Expose
    var avgSpawns: Double? = null

    @SerializedName("spawn_time")
    @Expose
    var spawnTime: String? = null

    @SerializedName("multipliers")
    @Expose
    var multipliers: List<Double>? = null

    @SerializedName("weaknesses")
    @Expose
    var weaknesses: List<String>? = null

    @SerializedName("next_evolution")
    @Expose
    var nextEvolution: List<NextEvolutionK>? = null

    @SerializedName("prev_evolution")
    @Expose
    var prevEvolution: List<PrevEvolutionK>? = null
}
