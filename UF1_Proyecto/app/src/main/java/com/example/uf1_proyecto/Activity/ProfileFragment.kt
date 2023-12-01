package com.example.uf1_proyecto.Activity

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.uf1_proyecto.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.Locale

class ProfileFragment : Fragment() {
    private lateinit var borrarBtn: Button
    private lateinit var cerrarSesionBtn: Button
    private lateinit var nombreUsuarioP: TextView
    private lateinit var Auth: FirebaseAuth
    private lateinit var user: FirebaseUser
    private lateinit var credits_btn: ImageView

    private lateinit var alonsoact: ImageView
    private lateinit var alonso: ImageView

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
        alonsoact = view.findViewById(R.id.shapeableImageView2)
        alonso = view.findViewById(R.id.elNano)
        credits_btn = view.findViewById(R.id.credits_btn)
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

        cerrarSesionBtn.setOnClickListener {
            mostrarDialogo(0)
        }

        borrarBtn.setOnClickListener {
            mostrarDialogo(1)
        }
        gal_language.setOnClickListener {
            onButtonLanguageClick("gl")
        }
        sp_language.setOnClickListener {
            onButtonLanguageClick("es")
        }
        cz_language.setOnClickListener {
            onButtonLanguageClick("cs")
        }
        en_language.setOnClickListener {
            onButtonLanguageClick("en")
        }
        credits_btn.setOnClickListener {
            val intent = Intent(requireContext(), CreditsActivity::class.java)
            startActivity(intent)
        }
        alonsoact.setOnClickListener {
            startAnimation()
        }
    }

    private fun startAnimation() {
        val animation: Animation = android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.elnanomagicalonso)
        alonso.startAnimation(animation)
    }

    private fun mostrarDialogo(cual:Int) {
        val builder = AlertDialog.Builder(requireContext())
        if (cual == 0) {
            builder.setTitle(getString(R.string.signoutacc))
            builder.setMessage(getString(R.string.signoutaccquestion))
        } else {
            builder.setTitle(getString(R.string.deleteacc))
            builder.setMessage(getString(R.string.deleteaccquestion))
        }
        builder.setPositiveButton(getString(R.string.yes)) { dialog, _ ->
            if (cual == 1) {
                FirebaseAuth.getInstance().currentUser!!.delete()
            } else if (cual == 0) {
                FirebaseAuth.getInstance().signOut()
            }

            val intent = Intent(requireContext(), IntroActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

            requireActivity().finishAffinity()

            dialog.dismiss()
        }

        builder.setNegativeButton(getString(R.string.no)) { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
    fun onButtonLanguageClick(idi: String) {
        val locale = Locale(idi)
        Locale.setDefault(locale)

        val configuration = Configuration()
        configuration.setLocale(locale)

        val resources = requireContext().resources
        resources.updateConfiguration(configuration, resources.displayMetrics)

        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finishAffinity()
    }
}