package com.example.movieapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.model.Movie
import kotlinx.android.synthetic.main.movie_layout.view.*

class MovieAdapter(private val movieList: List<Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val poster:ImageView = itemView.filmPoster
        val title = itemView.filmTitle
        val rating = itemView.filmRating
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = movieList[position].title
        holder.rating.text = movieList[position].voteAverage.toString()
    }

    override fun getItemCount(): Int {
        return movieList.size
    }


}