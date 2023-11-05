package com.example.uf1_proyecto.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.*
import com.android.volley.toolbox.*
import com.bumptech.glide.Glide
import com.example.uf1_proyecto.Adapter.*
import com.example.uf1_proyecto.Domain.*
import com.example.uf1_proyecto.R
import com.google.android.material.imageview.ShapeableImageView
import com.google.gson.Gson

class DetailActivity : AppCompatActivity() {
    private lateinit var mRequestQueue: RequestQueue
    private lateinit var mStringRequest: StringRequest
    private lateinit var progressBar: ProgressBar
    private lateinit var titleTxt: TextView
    private lateinit var movieRateTxt: TextView
    private lateinit var movieTimeTxt: TextView
    private lateinit var movieDateTxt: TextView
    private lateinit var movieSinopsisTxt: TextView
    private lateinit var movieActorsTxt: TextView
    private lateinit var scrollView: NestedScrollView
    private var idFilm: Int = 0
    private lateinit var pic1:ImageView
    private lateinit var pic2: ImageView
    private lateinit var backImg: ImageView
    private lateinit var adapterImgList: ImageListAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        idFilm = intent.getIntExtra("id", 0)
        initView()
        sendRequest()
    }

    private fun sendRequest() {
        mRequestQueue = Volley.newRequestQueue(this)
        progressBar.visibility = ProgressBar.VISIBLE
        scrollView.visibility = View.GONE

        val mStringRequest = StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/$idFilm",
            { response ->
                val gson = Gson()
                progressBar.visibility = ProgressBar.GONE
                scrollView.visibility = View.VISIBLE
                val items: FilmItem = gson.fromJson(response, FilmItem::class.java)
                Glide.with(this).load(items.poster).into(pic1)
                Glide.with(this).load(items.poster).into(pic2)
                titleTxt.text = items.title
                movieRateTxt.text = items.rated
                movieTimeTxt.text = items.runtime
                movieDateTxt.text = items.year
                movieSinopsisTxt.text = items.plot
                movieActorsTxt.text = items.actors
                if(items.images != null) {
                    adapterImgList = ImageListAdapter(items.images)
                    recyclerView.adapter = adapterImgList
                }
            },
            { error ->
                progressBar.visibility = ProgressBar.GONE
            }
        )
        mRequestQueue.add(mStringRequest)
    }

    private fun initView() {
        progressBar = findViewById(R.id.detailLoading)
        titleTxt = findViewById(R.id.movietitle)
        movieRateTxt = findViewById(R.id.stardetail)
        movieTimeTxt = findViewById(R.id.clockdetail)
        movieDateTxt = findViewById(R.id.calendardetail)
        movieSinopsisTxt = findViewById(R.id.sinopsistext)
        movieActorsTxt = findViewById(R.id.actorstext)
        scrollView = findViewById(R.id.scrollView4)
        pic1 = findViewById(R.id.movieposternormal)
        pic2 = findViewById(R.id.moviepostergradient)
        backImg = findViewById(R.id.backImg)
        recyclerView = findViewById(R.id.imagesRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        backImg.setOnClickListener{ finish() }

    }
}
