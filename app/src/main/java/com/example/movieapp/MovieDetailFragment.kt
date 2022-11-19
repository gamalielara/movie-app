package com.example.movieapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.model.Movie
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : Fragment() {

    private lateinit var movie: Movie
    private var bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movie = arguments?.getSerializable("movie") as Movie
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        movieTitle.text = movie.title
        movieDesc.text = movie.overview
        movieReleased.text = "Released on ${movie.releaseDate}"
        movieRating.text = "${movie.voteAverage}/10"

        val posterImage = "https://image.tmdb.org/t/p/original${movie.posterPath}"

        Glide.with(this).load(posterImage).apply(RequestOptions()).skipMemoryCache(true)
            .into(moviePoster)
    }
}