package com.example.uf1_proyecto.Activity

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.uf1_proyecto.R
import com.example.uf1_proyecto.R.anim
import com.example.uf1_proyecto.R.layout

class CreditsActivity : AppCompatActivity() {

    private lateinit var animado1: TextView
    private lateinit var backBtn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_credits)
        initView()

    }

    private fun initView() {
        backBtn = findViewById(R.id.backImg2)
        animado1 = findViewById(R.id.creditss12)
        anim1()
        backBtn.setOnClickListener {
            finish()
        }

    }

    fun anim1(){
        val animation: Animation = AnimationUtils.loadAnimation(this, anim.animation1)
        animado1.startAnimation(animation)

    }

}