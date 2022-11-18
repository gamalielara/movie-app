package com.example.movieapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.model.GetTopRatedMovies
import com.example.movieapp.model.Movie
import com.example.movieapp.service.ApiClient
import com.example.movieapp.service.ApiKey
import kotlinx.android.synthetic.main.fragment_now_playing.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedMoviesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchTopRatedMovies()
        bottomTabBarButton()
    }

    private fun fetchTopRatedMovies() {
        ApiClient.instance.getTopRatedMovies(ApiKey.API_KEY).enqueue(object :
            Callback<GetTopRatedMovies> {
            override fun onResponse(
                call: Call<GetTopRatedMovies>,
                response: Response<GetTopRatedMovies>
            ) {
                val body = response.body()
                if(body != null) showMovies(body.results)
            }

            override fun onFailure(call: Call<GetTopRatedMovies>, t: Throwable) {
                Log.e("GAGAL!!", t.message.toString())
            }
        })
    }

    private fun bottomTabBarButton () {
        popularMenuButton.setOnClickListener{
            findNavController().navigate(R.id.action_topRatedMoviesFragment_to_popularMoviesFragment)
        }

        nowPlayingButton.setOnClickListener{
            findNavController().navigate(R.id.action_topRatedMoviesFragment_to_nowPlayingFragment)
        }
    }

    private fun showMovies(movies: List<Movie>){
        val context = requireActivity()
        val adapter = MovieAdapter(movies, context)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        moviesList.layoutManager = linearLayoutManager
        moviesList.adapter = adapter
    }
}