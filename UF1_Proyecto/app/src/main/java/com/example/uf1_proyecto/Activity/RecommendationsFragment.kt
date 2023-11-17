package com.example.uf1_proyecto.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
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
    private lateinit var recyclerViewPrimGen: RecyclerView

    private lateinit var searchBtn: Button
    private lateinit var buscardor: EditText

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
                recyclerViewPrimGen.adapter = adapterPrimGen
            },
            { error ->
                loading1.visibility = View.GONE
            }
        )
        mRequestQueue.add(mStringRequest)
    }



    private fun initView(view: View) {
        recyclerViewPrimGen = view.findViewById(R.id.lastmovies)
        recyclerViewPrimGen.layoutManager = GridLayoutManager(requireContext(),3, LinearLayoutManager.VERTICAL, false)
        loading1 = view.findViewById(R.id.loading)
        searchBtn = view.findViewById(R.id.searchBtn)

        searchBtn.setOnClickListener {
            val navigator: NavController = findNavController()
            val bundle = Bundle()
            buscardor = view.findViewById(R.id.search_bar)
            bundle.putString("search", buscardor.text.toString())
            navigator.navigate(R.id.searchFragment, bundle)
        }

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