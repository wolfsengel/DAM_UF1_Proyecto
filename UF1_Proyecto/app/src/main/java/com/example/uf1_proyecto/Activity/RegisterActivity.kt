package com.example.uf1_proyecto.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.uf1_proyecto.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var userTxt: EditText
    private lateinit var passwordTxt: EditText
    private lateinit var passwordTxt2: EditText
    private lateinit var registerButton: Button
    private lateinit var login_reload: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()
    }

    private fun initView() {
        userTxt = findViewById(R.id.usernameR)
        passwordTxt = findViewById(R.id.passwordR)
        passwordTxt2 = findViewById(R.id.passwordR2)
        registerButton = findViewById(R.id.register_button_deltodo)
        login_reload = findViewById(R.id.reIniciaSesion)

        login_reload.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        registerButton.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}