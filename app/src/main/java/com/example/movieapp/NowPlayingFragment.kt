package com.example.movieapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.helper.RemoteDataSource
import com.example.movieapp.helper.Status
import com.example.movieapp.model.GetNowPlayingMovies
import com.example.movieapp.model.Movie
import com.example.movieapp.service.ApiClient
import com.example.movieapp.service.ApiKey
import com.example.movieapp.viewmodel.MovieViewModel
import com.example.movieapp.viewmodel.MoviewViewModelFactory
import kotlinx.android.synthetic.main.fragment_now_playing.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowPlayingFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        movieViewModel = ViewModelProvider(this, MoviewViewModelFactory(RemoteDataSource(ApiClient.instance)))[MovieViewModel::class.java]

        super.onViewCreated(view, savedInstanceState)

        fetchNowPlayingMovies(view)
        bottomTabBarButton()
    }

    private fun fetchNowPlayingMovies(view: View) {
        movieViewModel.getNowPlayingMovies().observe(viewLifecycleOwner){
                resource ->
            when(resource.status){
                Status.SUCCESS -> {
                    val response = resource.data
                    Log.e("FETCH NOW PLAYING MOVIES SUCCESS ", response.toString())
                    showMovies(response!!.results)
                }
                Status.ERROR -> {
                    val response = resource.message
                    Log.e("FETCH NOW PLAYING MOVIES FAILED ", response .toString())
                }
                Status.LOADING -> {}
            }
        }
    }

    private fun bottomTabBarButton () {
        popularMenuButton.setOnClickListener{
            findNavController().navigate(R.id.action_nowPlayingFragment_to_popularMoviesFragment)
        }

        topRatedMenuButton.setOnClickListener{
            findNavController().navigate(R.id.action_nowPlayingFragment_to_topRatedMoviesFragment)
        }
    }

    private fun showMovies(movies: List<Movie>){
        val context = requireActivity()
        val adapter = MovieAdapter(
            movies,
            { bundle: Bundle -> requireActivity().supportFragmentManager.setFragmentResult("redirectMovie", bundle)},
            context
        )

        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        moviesList.layoutManager = linearLayoutManager
        moviesList.adapter = adapter
    }


}