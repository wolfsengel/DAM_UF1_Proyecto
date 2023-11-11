package com.example.uf1_proyecto.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uf1_proyecto.Adapter.PokemonListAdapter
import com.example.uf1_proyecto.Domain.PokeItemK
import com.example.uf1_proyecto.R
import com.google.gson.Gson

class RecommendationsFragment : Fragment() {

    private lateinit var adapterPrimGen: PokemonListAdapter
    private lateinit var recyclerViewNewmovies: RecyclerView

    private lateinit var mRequestQueue: RequestQueue

    private lateinit var loading1: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun sendRequest1() {
        mRequestQueue = Volley.newRequestQueue(requireContext())
        loading1.visibility = View.VISIBLE

        val mStringRequest = StringRequest(
            Request.Method.GET, "https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json",
            { response ->
                val gson = Gson()
                loading1.visibility = View.GONE
                val items: PokeItemK = gson.fromJson(response, PokeItemK::class.java)
                adapterPrimGen = PokemonListAdapter(requireContext(),items)
                recyclerViewNewmovies.adapter = adapterPrimGen
            },
            { error ->
                loading1.visibility = View.GONE
            }
        )
        mRequestQueue.add(mStringRequest)
    }



    private fun initView(view: View) {
        recyclerViewNewmovies = view.findViewById(R.id.lastmovies)
        recyclerViewNewmovies.layoutManager = GridLayoutManager(requireContext(),3, LinearLayoutManager.VERTICAL, false)
        loading1 = view.findViewById(R.id.loading)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recommendations, container, false)
        initView(view)
        sendRequest1()
        return view
    }

}