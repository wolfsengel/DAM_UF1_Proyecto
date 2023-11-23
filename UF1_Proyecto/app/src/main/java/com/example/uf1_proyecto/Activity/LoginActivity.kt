package com.example.uf1_proyecto.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.uf1_proyecto.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var userTxt: EditText
    private lateinit var passwordTxt: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButtonL: TextView
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }
    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initView() {
        userTxt = findViewById(R.id.usernameTxt)
        passwordTxt = findViewById(R.id.passwordTxt)
        loginButton = findViewById(R.id.login_button)
        registerButtonL = findViewById(R.id.register_button)
        mAuth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener {
            if (userTxt.text.toString().isEmpty() || passwordTxt.text.toString().isEmpty()) {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth.signInWithEmailAndPassword(userTxt.text.toString(), passwordTxt.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
                    }
                }

        }
        registerButtonL.setOnClickListener{
            val intent2 = Intent(this, RegisterActivity::class.java)
            startActivity(intent2)
            finish()
        }
    }
}