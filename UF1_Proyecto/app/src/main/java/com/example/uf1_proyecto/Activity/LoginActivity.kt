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

class LoginActivity : AppCompatActivity() {
    private lateinit var userTxt: EditText
    private lateinit var passwordTxt: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButtonL: TextView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var progressBar: ProgressBar
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
        progressBar = findViewById(R.id.progressBar)

        loginButton.setOnClickListener {
            progressBar.visibility = ProgressBar.VISIBLE
            if (userTxt.text.toString().isEmpty() || passwordTxt.text.toString().isEmpty()) {
                Toast.makeText(this, getString(R.string.noemptyspaces), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth.signInWithEmailAndPassword(userTxt.text.toString(), passwordTxt.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        progressBar.visibility = ProgressBar.GONE
                        val intent = Intent(this, MainActivity::class.java)
                        Toast.makeText(this, getString(R.string.correctlogin), Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                        finish()
                    } else {
                        progressBar.visibility = ProgressBar.GONE
                        Toast.makeText(this, getString(R.string.incorrectlogin), Toast.LENGTH_SHORT).show()
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