package com.example.movieapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.movieapp.model.GetPopularMovies
import com.example.movieapp.model.Movie
import com.example.movieapp.service.ApiClient
import com.example.movieapp.service.ApiKey
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchPopularMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun fetchPopularMovies() {
        ApiClient.instance.getPopularMovies(ApiKey.API_KEY).enqueue(object :
            Callback<GetPopularMovies> {
            override fun onResponse(
                call: Call<GetPopularMovies>,
                response: Response<GetPopularMovies>
            ) {
                val body = response.body()
                if(body != null) showMovies(body.results)
            }

            override fun onFailure(call: Call<GetPopularMovies>, t: Throwable) {
                Log.e("GAGAL!!", t.message.toString())
            }
        })
    }

    private fun showMovies(movies: List<Movie>){
        val context = requireActivity()
        val adapter = MovieAdapter(movies, context)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        moviesList.layoutManager = linearLayoutManager
        moviesList.adapter = adapter
    }
}