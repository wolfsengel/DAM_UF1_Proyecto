package com.example.uf1_proyecto.Activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.uf1_proyecto.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ProfileFragment : Fragment() {
    private lateinit var borrarBtn: Button
    private lateinit var cerrarSesionBtn: Button
    private lateinit var nombreUsuarioP: TextView
    private lateinit var Auth: FirebaseAuth
    private lateinit var user: FirebaseUser

    private lateinit var gal_language: ImageView
    private lateinit var sp_language: ImageView
    private lateinit var cz_language: ImageView
    private lateinit var en_language: ImageView
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
        gal_language = view.findViewById(R.id.galician_flag)
        sp_language = view.findViewById(R.id.spanish_flag)
        cz_language = view.findViewById(R.id.czech_flag)
        en_language = view.findViewById(R.id.english_flag)
        borrarBtn = view.findViewById(R.id.erase_account_button)
        cerrarSesionBtn = view.findViewById(R.id.logout_button)
        nombreUsuarioP = view.findViewById(R.id.usernametag)
        Auth = FirebaseAuth.getInstance()
        user = Auth.currentUser!!
        nombreUsuarioP.text = user.email

        val intent = Intent(requireContext(), IntroActivity::class.java)

        borrarBtn.setOnClickListener {
            val alertDialog: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Borrar cuenta")
            alertDialog.setMessage("¿Estás seguro de que quieres borrar tu cuenta?")
            alertDialog.setPositiveButton("Sí") { _, _ ->
                FirebaseAuth.getInstance().currentUser!!.delete()
                Toast.makeText(requireContext(), "Borrado", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
            alertDialog.setNegativeButton("No") { _, _ ->
                Toast.makeText(requireContext(), "Cancelado", Toast.LENGTH_SHORT).show()
            }
            alertDialog.show()
        }
        cerrarSesionBtn.setOnClickListener {
            val alertDialog: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Cerrar sesión")
            alertDialog.setMessage("¿Estás seguro de que quieres cerrar sesión?")
            alertDialog.setPositiveButton("Sí") { _, _ ->
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(requireContext(), "Cerrando sesión", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
            alertDialog.setNegativeButton("No") { _, _ ->
                Toast.makeText(requireContext(), "Cancelado", Toast.LENGTH_SHORT).show()
            }
            alertDialog.show()
        }

        gal_language.setOnClickListener {
            val alertDialog: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Cambiar idioma")
            alertDialog.setMessage("Estás seguro de que queres cambiar o idioma a galego?")
            alertDialog.setPositiveButton("Sí") { _, _ ->
                Toast.makeText(requireContext(), "Idioma cambiado", Toast.LENGTH_SHORT).show()
            }
            alertDialog.setNegativeButton("Non") { _, _ ->
                Toast.makeText(requireContext(), "Cancelado", Toast.LENGTH_SHORT).show()
            }
            alertDialog.show()
        }
        sp_language.setOnClickListener {
            val alertDialog: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Cambiar idioma")
            alertDialog.setMessage("Estás seguro de que quieres cambiar el idioma a español?")
            alertDialog.setPositiveButton("Sí") { _, _ ->
                Toast.makeText(requireContext(), "Idioma cambiado", Toast.LENGTH_SHORT).show()
            }
            alertDialog.setNegativeButton("No") { _, _ ->
                Toast.makeText(requireContext(), "Cancelado", Toast.LENGTH_SHORT).show()
            }
            alertDialog.show()
        }
        cz_language.setOnClickListener {
            val alertDialog: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Změnit jazyk")
            alertDialog.setMessage("Jste si jisti, že chcete změnit jazyk na češtinu?")
            alertDialog.setPositiveButton("Ano") { _, _ ->
                Toast.makeText(requireContext(), "Jazyk změněn", Toast.LENGTH_SHORT).show()
            }
            alertDialog.setNegativeButton("Ne") { _, _ ->
                Toast.makeText(requireContext(), "Zrušeno", Toast.LENGTH_SHORT).show()
            }
            alertDialog.show()
        }
        en_language.setOnClickListener {
            val alertDialog: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Change language")
            alertDialog.setMessage("Are you sure you want to change the language to English?")
            alertDialog.setPositiveButton("Yes") { _, _ ->
                Toast.makeText(requireContext(), "Language changed", Toast.LENGTH_SHORT).show()
            }
            alertDialog.setNegativeButton("No") { _, _ ->
                Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_SHORT).show()
            }
            alertDialog.show()
        }
    }

}