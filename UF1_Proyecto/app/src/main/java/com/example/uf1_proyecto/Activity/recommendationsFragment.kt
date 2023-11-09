package com.example.uf1_proyecto.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uf1_proyecto.Adapter.*
import com.example.uf1_proyecto.Domain.*
import com.example.uf1_proyecto.R
import com.google.gson.Gson

class recommendationsFragment : Fragment() {

    private lateinit var adapterNewMovies: FilmListAdapter
    private lateinit var adapterPopular: FilmListAdapter
    private lateinit var adapterToprated: FilmListAdapter
    private lateinit var recyclerViewNewmovies: RecyclerView
    private lateinit var recyclerViewPopular: RecyclerView
    private lateinit var recyclerViewToprated: RecyclerView

    private lateinit var mRequestQueue: RequestQueue

    private lateinit var loading1: ProgressBar
    private lateinit var loading2: ProgressBar
    private lateinit var loading3: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun sendRequest1() {
        mRequestQueue = Volley.newRequestQueue(requireContext())
        loading1.visibility = View.VISIBLE

        val mStringRequest = StringRequest(
            Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=11",
            { response ->
                val gson = Gson()
                loading1.visibility = View.GONE
                val items: ListFilm = gson.fromJson(response, ListFilm::class.java)
                adapterNewMovies = FilmListAdapter(items)
                recyclerViewNewmovies.adapter = adapterNewMovies
            },
            { error ->
                loading1.visibility = View.GONE
            }
        )

        mRequestQueue.add(mStringRequest)
    }

    private fun sendRequest2() {
        mRequestQueue = Volley.newRequestQueue(requireContext())
        loading2.visibility = View.VISIBLE

        val mStringRequest2 = StringRequest(
            Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=21",
            { response ->
                val gson = Gson()
                loading2.visibility = View.GONE
                val items: ListFilm = gson.fromJson(response,ListFilm::class.java)
                adapterPopular = FilmListAdapter(items)
                recyclerViewPopular.adapter = adapterPopular
            },
            { error ->
                loading2.visibility = View.GONE
            }
        )

        mRequestQueue.add(mStringRequest2)

    }
    private fun sendRequest3() {
        mRequestQueue = Volley.newRequestQueue(requireContext())
        loading3.visibility = View.VISIBLE

        val mStringRequest3 = StringRequest(
            Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=15",
            { response ->
                val gson = Gson()
                loading3.visibility = View.GONE
                val items: ListFilm = gson.fromJson(response,ListFilm::class.java)
                adapterToprated = FilmListAdapter(items)
                recyclerViewToprated.adapter = adapterToprated
            },
            { error ->
                loading3.visibility = View.GONE
            }
        )
        mRequestQueue.add(mStringRequest3)

    }


    private fun initView(view: View) {
        recyclerViewNewmovies = view.findViewById(R.id.lastmovies)
        recyclerViewNewmovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPopular = view.findViewById(R.id.popularmovies)
        recyclerViewPopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewToprated = view.findViewById(R.id.topmovies)
        recyclerViewToprated.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        loading1 = view.findViewById(R.id.loading)
        loading2 = view.findViewById(R.id.loading2)
        loading3 = view.findViewById(R.id.loading3)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recommendations, container, false)
        initView(view)
        sendRequest1()
        sendRequest2()
        sendRequest3()
        return view
    }

}