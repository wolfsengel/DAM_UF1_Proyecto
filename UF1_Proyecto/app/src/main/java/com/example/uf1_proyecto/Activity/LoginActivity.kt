package com.example.uf1_proyecto.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.uf1_proyecto.R

class LoginActivity : AppCompatActivity() {
    private lateinit var userTxt: EditText
    private lateinit var passwordTxt: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    private fun initView() {
        userTxt = findViewById(R.id.usernameTxt)
        passwordTxt = findViewById(R.id.passwordTxt)
        loginButton = findViewById(R.id.login_button)
        registerButton = findViewById(R.id.register_button)

        loginButton.setOnClickListener {
            if (userTxt.text.toString().isEmpty() || passwordTxt.text.toString().isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else if (userTxt.text.toString() == "test" && passwordTxt.text.toString() == "test") {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        registerButton.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}