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
    private lateinit var panel: ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_credits)
        initView()

    }

    private fun initView() {
        panel = findViewById(R.id.container_credits)
        animado1 = findViewById(R.id.creditss11)
        animado2 = findViewById(R.id.creditss12)
        animado3 = findViewById(R.id.creditss13)
        animado4 = findViewById(R.id.creditss14)
        animado5 = findViewById(R.id.creditss15)
        animado6 = findViewById(R.id.creditss16)
        animado7 = findViewById(R.id.creditss17)
        animado8 = findViewById(R.id.creditss18)
        animado9 = findViewById(R.id.creditss19)
        anim1()
        //anim2()
        //anim3()
        //anim4()
        //anim5()
        //anim6()
        //anim7()
        //anim8()
        //anim9()

    }

    fun anim1(){
        val animation: Animation = AnimationUtils.loadAnimation(this, anim.animation1)
        animado1.startAnimation(animation)

    }
    fun anim2(){
        val animation: Animation = AnimationUtils.loadAnimation(this, anim.animation2)
        animado2.startAnimation(animation)
    }
    fun anim3(){
        val animation: Animation = AnimationUtils.loadAnimation(this, anim.animation3)
        animado3.startAnimation(animation)
    }
    fun anim4(){
        val animation: Animation = AnimationUtils.loadAnimation(this, anim.animation4)
        animado4.startAnimation(animation)
    }
    fun anim5(){
        val animation: Animation = AnimationUtils.loadAnimation(this, anim.animation5)
        animado5.startAnimation(animation)
    }
    fun anim6(){
        val animation: Animation = AnimationUtils.loadAnimation(this, anim.animation6)
        animado6.startAnimation(animation)
    }
    fun anim7(){
        val animation: Animation = AnimationUtils.loadAnimation(this, anim.animation7)
        animado7.startAnimation(animation)
    }
    fun anim8(){
        val animation: Animation = AnimationUtils.loadAnimation(this, anim.animation8)
        animado8.startAnimation(animation)
    }
    fun anim9(){
        val animation: Animation = AnimationUtils.loadAnimation(this, anim.animation9)
        animado9.startAnimation(animation)
    }


}