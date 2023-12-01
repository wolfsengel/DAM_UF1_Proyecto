package com.example.uf1_proyecto.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.uf1_proyecto.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        findViewById<View>(R.id.profile).setOnClickListener {
            navController.navigate(R.id.profileFragment)
        }

        findViewById<View>(R.id.catalogo).setOnClickListener {
            navController.navigate(R.id.guessGameFragment)
        }

        findViewById<View>(R.id.homeButton).setOnClickListener {
            navController.navigate(R.id.recommendationsFragment)
        }
    }
}
