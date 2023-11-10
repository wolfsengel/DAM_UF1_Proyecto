package com.example.uf1_proyecto.Domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PrevEvolutionK {
    @SerializedName("num")
    @Expose
    var num: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null
}
