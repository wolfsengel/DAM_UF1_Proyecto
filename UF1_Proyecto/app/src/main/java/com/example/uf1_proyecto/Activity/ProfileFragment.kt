package com.example.uf1_proyecto.Activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.uf1_proyecto.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ProfileFragment : Fragment() {
    private lateinit var borrarBtn: Button
    private lateinit var cerrarSesionBtn: Button
    private lateinit var nombreUsuarioP: TextView
    private lateinit var Auth: FirebaseAuth
    private lateinit var user: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        borrarBtn = view.findViewById(R.id.erase_account_button)
        cerrarSesionBtn = view.findViewById(R.id.logout_button)
        nombreUsuarioP = view.findViewById(R.id.usernametag)
        Auth = FirebaseAuth.getInstance()
        user = Auth.currentUser!!
        nombreUsuarioP.text = user.email

        val intent = Intent(requireContext(), IntroActivity::class.java)

        borrarBtn.setOnClickListener {
            FirebaseAuth.getInstance().currentUser!!.delete()
            Toast.makeText(requireContext(), "Borrado", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
        cerrarSesionBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(requireContext(), "Cerrando sesión", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }

}