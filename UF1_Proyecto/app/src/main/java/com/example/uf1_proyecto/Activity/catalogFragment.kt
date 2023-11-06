package com.example.uf1_proyecto.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.*
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uf1_proyecto.Adapter.*
import com.example.uf1_proyecto.Domain.*
import com.example.uf1_proyecto.R
import com.google.gson.Gson

class catalogFragment : Fragment() {

    private lateinit var adapterCinesa: FilmListAdapter
    private lateinit var adapterYelmo: FilmListAdapter
    private lateinit var adapterPaco: FilmListAdapter

    private lateinit var recyclerViewCinesa: RecyclerView
    private lateinit var recyclerViewYelmo: RecyclerView
    private lateinit var recyclerViewPaco: RecyclerView

    private lateinit var loading1: ProgressBar
    private lateinit var loading2: ProgressBar
    private lateinit var loading3: ProgressBar

    private lateinit var mRequestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_catalog, container, false)
        initView(view)
        sendRequest1()
        sendRequest2()
        sendRequest3()
        return view
    }

    private fun sendRequest1() {
        mRequestQueue = Volley.newRequestQueue(requireContext())
        loading1.visibility = View.VISIBLE

        val mStringRequest = StringRequest(
            Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=4",
            { response ->
                val gson = Gson()
                loading1.visibility = View.GONE
                val items: ListFilm = gson.fromJson(response, ListFilm::class.java)
                adapterCinesa = FilmListAdapter(items)
                recyclerViewCinesa.adapter = adapterCinesa
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

        val mStringRequest = StringRequest(
            Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=5",
            { response ->
                val gson = Gson()
                loading2.visibility = View.GONE
                val items: ListFilm = gson.fromJson(response, ListFilm::class.java)
                adapterYelmo = FilmListAdapter(items)
                recyclerViewYelmo.adapter = adapterYelmo
            },
            { error ->
                loading2.visibility = View.GONE
            }
        )

        mRequestQueue.add(mStringRequest)
    }
    private fun sendRequest3() {
        mRequestQueue = Volley.newRequestQueue(requireContext())
        loading3.visibility = View.VISIBLE

        val mStringRequest = StringRequest(
            Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=6",
            { response ->
                val gson = Gson()
                loading3.visibility = View.GONE
                val items: ListFilm = gson.fromJson(response, ListFilm::class.java)
                adapterPaco = FilmListAdapter(items)
                recyclerViewPaco.adapter = adapterPaco
            },
            { error ->
                loading3.visibility = View.GONE
            }
        )

        mRequestQueue.add(mStringRequest)
    }


    private fun initView(view: View) {
        recyclerViewCinesa = view.findViewById(R.id.cinesaviewC)
        recyclerViewCinesa.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewYelmo = view.findViewById(R.id.yelmoviewC)
        recyclerViewYelmo.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPaco = view.findViewById(R.id.pacocinesviewC)
        recyclerViewPaco.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        loading1 = view.findViewById(R.id.loadingC)
        loading2 = view.findViewById(R.id.loadingC2)
        loading3 = view.findViewById(R.id.loadingC3)
    }
}