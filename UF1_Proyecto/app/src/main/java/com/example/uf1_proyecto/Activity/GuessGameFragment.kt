package com.example.uf1_proyecto.Activity

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.uf1_proyecto.Domain.PokeItemK
import com.example.uf1_proyecto.R
import com.google.gson.Gson

class GuessGameFragment : Fragment() {

    private lateinit var guess_txt: TextView
    private lateinit var guess_btn: Button
    private lateinit var reveal_btn: Button
    private lateinit var refresh_btn: Button
    private lateinit var guesspk_txt: TextView
    private lateinit var new_btn: Button
    private lateinit var pk_img: ImageView
    private lateinit var highscore_txt: TextView
    private lateinit var currentscore_txt: TextView
    private var highscore = 0

    private lateinit var mRequestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        loadHighscore()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_guessgame, container, false)
        initView(view)
        loadHighscore()
        return view
    }

    private fun sendRequest1(random:Int) {

        mRequestQueue = Volley.newRequestQueue(requireContext())

        val mStringRequest = StringRequest(
            Request.Method.GET, "https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json",
            { response ->
                val gson = Gson()
                val items: PokeItemK = gson.fromJson(response, PokeItemK::class.java)
                val itemsFiltered: PokeItemK = items
                itemsFiltered.pokemon = items.pokemon!!.filter { it.id == random }
                guesspk_txt.text = itemsFiltered.pokemon!![0].name
                Glide.with(requireContext()).load(itemsFiltered.pokemon!![0].img).into(pk_img)
                applySilhouetteEffect(pk_img)
            },
            { error ->
            }
        )
        mRequestQueue.add(mStringRequest)

    }

    private fun initView(view: View) {
        loadHighscore()
        var score = 0
        var revealed = false
        guess_txt = view.findViewById(R.id.guess_txt)
        highscore_txt = view.findViewById(R.id.highscore)
        guess_btn = view.findViewById(R.id.guess_btn)
        reveal_btn = view.findViewById(R.id.reveal_btn)
        refresh_btn = view.findViewById(R.id.refresh_btn)
        guesspk_txt = view.findViewById(R.id.secretpk)
        new_btn = view.findViewById(R.id.newone_btn)
        pk_img = view.findViewById(R.id.secret_pk_img)
        currentscore_txt = view.findViewById(R.id.current_user_score)
        guesspk_txt.visibility = View.INVISIBLE

        val random = (1..151).random()
        sendRequest1(random)

        val highscoreManager = HighscoreManager(this.requireContext())

        val loadedHighscore = highscoreManager.loadHighscore()
        highscore_txt.text = loadedHighscore.toString()

        refresh_btn.setOnClickListener {
            revealed = false
            guess_txt.setText("")
            guesspk_txt.visibility = View.INVISIBLE
            val random = (1..151).random()
            sendRequest1(random)
            if (score>0) {
                score--
                currentscore_txt.text = score.toString()
            }
        }

        guess_btn.setOnClickListener {
            if (revealed) {
                Toast.makeText(requireContext(), getString(R.string.alreadyreveal) , Toast.LENGTH_SHORT).show()
                guesspk_txt.visibility = View.INVISIBLE
                val random = (1..151).random()
                sendRequest1(random)
                revealed=false
                if (score>0) {
                    score--
                    currentscore_txt.text = score.toString()
                }
                currentscore_txt.text = score.toString()

            }
            if (guess_txt.text.toString().uppercase() == guesspk_txt.text.toString().uppercase()) {
                Toast.makeText(requireContext(), getString(R.string.correct), Toast.LENGTH_SHORT).show()
                revealed = false
                guess_txt.setText("")
                guesspk_txt.visibility = View.VISIBLE
                removeSilhouetteEffect(pk_img)
                score++
                currentscore_txt.text = score.toString()
                if (score>highscore_txt.text.toString().toInt()) {
                    highscore_txt.text = score.toString()
                    highscore = score
                    highscoreManager.saveHighscore(highscore)
                }
            } else {
                Toast.makeText(requireContext(), getString(R.string.incorrect), Toast.LENGTH_SHORT).show()
                revealed = false
                guess_txt.setText("")
                guesspk_txt.visibility = View.INVISIBLE
            }
        }

        reveal_btn.setOnClickListener {
            revealed = false
            removeSilhouetteEffect(pk_img)
            guesspk_txt.visibility = View.VISIBLE
            if (score>0) {
                score--
                currentscore_txt.text = score.toString()
            }
        }

        new_btn.setOnClickListener {
            guesspk_txt.visibility = View.INVISIBLE
            val random = (1..151).random()
            sendRequest1(random)
        }

    }

    inner class HighscoreManager(private val context: Context) {

        private val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("HighscorePrefs", Context.MODE_PRIVATE)

        fun saveHighscore(score: Int) {
            val editor = sharedPreferences.edit()
            editor.putInt("highscore", score)
            editor.apply()
        }

        fun loadHighscore(): Int {
            return sharedPreferences.getInt("highscore", 0)
        }
    }



    fun saveHighscore(score:Int) {
        //shared preferences

    }

    fun loadHighscore() {

    }
    private fun applySilhouetteEffect(imageView: ImageView) {
        imageView.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN)
    }
    private fun removeSilhouetteEffect(imageView: ImageView) {
        imageView.clearColorFilter()
    }


}