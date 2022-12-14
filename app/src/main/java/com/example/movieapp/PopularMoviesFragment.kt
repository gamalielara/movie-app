package com.example.movieapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.model.GetPopularMovies
import com.example.movieapp.model.Movie
import com.example.movieapp.service.ApiClient
import com.example.movieapp.service.ApiKey
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.fragment_now_playing.nowPlayingButton as nowPlayingButton1
import kotlinx.android.synthetic.main.fragment_popular_movies.topRatedMenuButton as topRatedMenuButton1

class PopularMoviesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomTabBarButton()
        fetchPopularMovies()
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

    private fun bottomTabBarButton () {
        nowPlayingButton1.setOnClickListener{
            findNavController().navigate(R.id.action_popularMoviesFragment_to_nowPlayingFragment)
        }

        topRatedMenuButton1.setOnClickListener{
            findNavController().navigate(R.id.action_popularMoviesFragment_to_topRatedMoviesFragment)
        }
    }

    private fun showMovies(movies: List<Movie>){
        val context = requireActivity()
        val adapter =
            MovieAdapter(
                movies,
                { bundle: Bundle -> requireActivity().supportFragmentManager.setFragmentResult("redirectMovie", bundle)},
                context
            )
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        moviesList.layoutManager = linearLayoutManager
        moviesList.adapter = adapter
    }
}