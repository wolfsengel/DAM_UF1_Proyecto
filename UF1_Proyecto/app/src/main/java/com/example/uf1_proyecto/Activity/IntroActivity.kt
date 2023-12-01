package com.example.uf1_proyecto.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.*
import com.example.uf1_proyecto.R

class IntroActivity : AppCompatActivity() {

    private lateinit var pikanano: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        val getInButton: AppCompatButton = findViewById(R.id.getInButton)
        initView()
        getInButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initView() {
        pikanano = findViewById(R.id.pikaintroimg)
        pikanano.setOnClickListener{
            pikanano.setImageResource(R.drawable.nanochu)
        }

    }

}