package com.example.uf1_proyecto.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uf1_proyecto.R
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var userTxt: EditText
    private lateinit var passwordTxt: EditText
    private lateinit var passwordTxt2: EditText
    private lateinit var registerButton: Button
    private lateinit var login_reload: TextView
    private lateinit var mAuth:FirebaseAuth
    private lateinit var progressBar: ProgressBar

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
        progressBar = findViewById(R.id.progressBar2)
        mAuth = FirebaseAuth.getInstance()
progressBar.visibility = ProgressBar.GONE
        login_reload.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        registerButton.setOnClickListener {
            if (userTxt.text.toString().isEmpty() || passwordTxt.text.toString().isEmpty() || passwordTxt2.text.toString().isEmpty()) {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (passwordTxt.text.toString() == passwordTxt2.text.toString()) {

                progressBar.visibility = ProgressBar.VISIBLE
                val intent = Intent(this, LoginActivity::class.java)

                intent.putExtra("username", userTxt.text.toString())
                intent.putExtra("password", passwordTxt.text.toString())

                mAuth.createUserWithEmailAndPassword(userTxt.text.toString(), passwordTxt.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        progressBar.visibility = ProgressBar.GONE
                        Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    } else {
                        progressBar.visibility = ProgressBar.GONE
                        Toast.makeText(this, "Error al crear el usuario", Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                progressBar.visibility = ProgressBar.GONE
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }
    }
}