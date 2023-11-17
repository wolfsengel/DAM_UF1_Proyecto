package com.example.uf1_proyecto.Activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.uf1_proyecto.R

class DetailActivity : AppCompatActivity() {
    private lateinit var pokemonName: TextView
    private lateinit var pokemonId: TextView
    private lateinit var pokemonHeight: TextView
    private lateinit var pokemonWeight: TextView
    private lateinit var pokemonImage: ImageView
    private lateinit var pokemonImagegradient: ImageView

    private lateinit var pokemonType: TextView
    private lateinit var pokemonCandy: TextView
    private lateinit var pokemonCandyCount: TextView
    private lateinit var pokemonEgg: TextView
    private lateinit var pokemonSpawnChance: TextView
    private lateinit var pokemonAvgSpawns: TextView
    private lateinit var pokemonSpawnTime: TextView

    private lateinit var backButton: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initView()

    }



    private fun initView() {

        pokemonName = findViewById(R.id.pokemonname)
        pokemonId = findViewById(R.id.pokeId_detail)
        pokemonHeight = findViewById(R.id.pokeHeight)
        pokemonWeight = findViewById(R.id.pokeWeight)
        pokemonImage = findViewById(R.id.pokemonimagenormal)
        pokemonImagegradient = findViewById(R.id.pokemongradient)
        pokemonCandy = findViewById(R.id.candy_text)
        pokemonCandyCount = findViewById(R.id.candycount_text)
        pokemonEgg = findViewById(R.id.egg_text)
        pokemonSpawnChance = findViewById(R.id.spawnchance_text)
        pokemonAvgSpawns = findViewById(R.id.spawn_text)
        pokemonSpawnTime = findViewById(R.id.timespawn_text)
        pokemonType = findViewById(R.id.typetext)
        backButton = findViewById(R.id.backImg)

        backButton.setOnClickListener {
            finish()
        }

        val intent = intent
        val name = intent.getStringExtra("pokemonName")
        val id = intent.getStringExtra("pokemonId")
        val height = intent.getStringExtra("pokemonHeight")
        val weight = intent.getStringExtra("pokemonWeight")
        val image = intent.getStringExtra("pokemonImage")
        val type = intent.getStringExtra("pokemonType")
        val candy = intent.getStringExtra("pokemonCandy")
        val candyCount = intent.getStringExtra("pokemonCandyCount")
        val egg = intent.getStringExtra("pokemonEgg")
        val spawnChance = intent.getStringExtra("pokemonSpawnChance")
        val avgSpawns = intent.getStringExtra("pokemonAvgSpawns")
        val spawnTime = intent.getStringExtra("pokemonSpawnTime")


        pokemonName.text = name
        pokemonId.text = id
        pokemonHeight.text = height
        pokemonWeight.text = weight
        pokemonType.text = type
        pokemonCandy.text = candy
        pokemonCandyCount.text = candyCount
        pokemonEgg.text = egg
        pokemonSpawnChance.text = spawnChance
        pokemonAvgSpawns.text = avgSpawns
        pokemonSpawnTime.text = spawnTime

        Glide.with(this).load(image).into(pokemonImagegradient)
        Glide.with(this).load(image).into(pokemonImage)

    }
}
