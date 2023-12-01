package com.example.uf1_proyecto.Activity

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.uf1_proyecto.R
import com.example.uf1_proyecto.R.anim
import com.example.uf1_proyecto.R.layout
import kotlinx.coroutines.delay

class CreditsActivity : AppCompatActivity() {

    private lateinit var animado1: TextView
    private lateinit var animado2: TextView
    private lateinit var animado3: TextView
    private lateinit var animado4: TextView
    private lateinit var animado5: TextView
    private lateinit var animado6: TextView
    private lateinit var animado7: TextView
    private lateinit var animado8: TextView
    private lateinit var animado9: TextView
    private lateinit var panel: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_credits)
        initView()

    }

    private fun initView() {
        panel = findViewById(R.id.container_credits)
        animado1 = findViewById(R.id.creditss11)
        anim1()

    }

    fun anim1(){
        val animation: Animation = AnimationUtils.loadAnimation(this, anim.animation1)
        animado1.startAnimation(animation)

    }

}