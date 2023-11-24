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

class CatalogFragment : Fragment() {

    private lateinit var adapterCinesa: PokemonListAdapter

    private lateinit var recyclerViewCinesa: RecyclerView

    private lateinit var loading1: ProgressBar

    private lateinit var mRequestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_guessgame, container, false)
        initView(view)
        sendRequest1()
        return view
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
                adapterCinesa = PokemonListAdapter(requireContext(),items)
                recyclerViewCinesa.adapter = adapterCinesa
            },
            { error ->
                loading1.visibility = View.GONE
            }
        )

        mRequestQueue.add(mStringRequest)
    }

    private fun initView(view: View) {
        recyclerViewCinesa = view.findViewById(R.id.cinesaviewC)

        val layoutManager = GridLayoutManager(requireContext(), 3, LinearLayoutManager.VERTICAL, false)
        recyclerViewCinesa.layoutManager = layoutManager

        loading1 = view.findViewById(R.id.loadingC)
    }

}