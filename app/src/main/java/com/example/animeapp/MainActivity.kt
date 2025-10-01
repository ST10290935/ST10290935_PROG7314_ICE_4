package com.example.animeapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animeapp.model.Anime
import com.example.animeapp.model.AnimeResponse
import com.example.animeapp.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AnimeAdapter
    private val animeList = mutableListOf<Anime>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = AnimeAdapter(animeList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Example search: Naruto, page = 1, size = 10
        fetchAnime(query = "One Piece", page = 1, size = 10)
    }

    private fun fetchAnime(query: String, page: Int, size: Int) {
        RetrofitInstance.api.searchAnime(page, size, query).enqueue(object : Callback<AnimeResponse> {
            override fun onResponse(call: Call<AnimeResponse>, response: Response<AnimeResponse>) {
                Log.d("MainActivity", "HTTP Code: ${response.code()}")

                if (response.isSuccessful) {
                    val results = response.body()?.results

                    if (!results.isNullOrEmpty()) {
                        animeList.clear()
                        animeList.addAll(results)
                        adapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this@MainActivity, "No anime found", Toast.LENGTH_SHORT).show()
                        Log.d("MainActivity", "Response body is empty or null")
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "API Error: ${response.code()} ${response.message()}",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.e("MainActivity", "API Error: ${response.code()} ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<AnimeResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Network Error: ${t.message}", Toast.LENGTH_LONG).show()
                Log.e("MainActivity", "Network Error", t)
            }
        })
    }
}
