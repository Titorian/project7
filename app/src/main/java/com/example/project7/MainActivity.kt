package com.example.project7

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.project7.databinding.ActivityMainBinding
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.button.setOnClickListener {
            pokeinfor()
        }
    }

    fun pokeinfor() {
        val client = AsyncHttpClient()

        client["https://pokeapi.co/api/v2/pokemon/ditto", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.i("Poke", json.toString())

                Log.i("Poke", json.jsonObject.getJSONArray("abilities").getJSONObject(1).getJSONObject("ability").getString("name")) //ability name "imposter"
                Log.i("Poke", json.jsonObject.getJSONArray("held_items").getJSONObject(0).getJSONObject("item").getString("name")) // held items "metal powder"
                Log.i("Poke", json.jsonObject.getJSONArray("moves").getJSONObject(0).getJSONObject("move").getString("name")) // move name "transform"
                binding.pokemonfact1.text = json.jsonObject.getJSONArray("abilities").getJSONObject(1).getJSONObject("ability").getString("name").uppercase()
                binding.pokemonfact2.text = json.jsonObject.getJSONArray("held_items").getJSONObject(0).getJSONObject("item").getString("name").uppercase()
                binding.pokemonfact3.text = json.jsonObject.getJSONArray("moves").getJSONObject(0).getJSONObject("move").getString("name").uppercase()
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Poke Error", errorResponse)
            }
        }]
    }











}