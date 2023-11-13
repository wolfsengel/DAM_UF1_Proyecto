package com.example.uf1_proyecto.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uf1_proyecto.Adapter.PokemonListAdapter
import com.example.uf1_proyecto.Domain.PokeItemK
import com.example.uf1_proyecto.R
import com.google.gson.Gson

class SearchResultsFragment : Fragment() {

    private lateinit var adapterResultados: PokemonListAdapter
    private lateinit var recyclerResultados: RecyclerView

    private var busca: String? = arguments?.getString("busqueda")

    private lateinit var searchTxt: TextView

    private lateinit var mRequestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_results, container, false)

        initView(view)
        sendRequest()

        return view
    }

    private fun sendRequest() {
        mRequestQueue = Volley.newRequestQueue(requireContext())

        val mStringRequest = StringRequest(
            Request.Method.GET, "https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json",
            { response ->
                val gson = Gson()
                val items: PokeItemK = gson.fromJson(response, PokeItemK::class.java)
                val itemsFiltered: PokeItemK = items
                itemsFiltered.pokemon = items.pokemon!!.filter { it.name!!.contains(busca.toString(), true)}
                adapterResultados = PokemonListAdapter(requireContext(),itemsFiltered)
                recyclerResultados.adapter = adapterResultados
            },
            { error ->
            }
        )
        mRequestQueue.add(mStringRequest)
    }

    private fun initView(view: View) {
        searchTxt = view.findViewById(R.id.busquedaTxt)
        searchTxt.text = busca
        recyclerResultados = view.findViewById(R.id.resultados)
        recyclerResultados.layoutManager = androidx.recyclerview.widget.GridLayoutManager(requireContext(),3, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)
    }

}