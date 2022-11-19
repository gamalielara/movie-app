package com.example.movieapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.model.Movie
import kotlinx.android.synthetic.main.movie_layout.view.*

class MovieAdapter(
    private val movieList: List<Movie>,
    private val onClick: (Bundle) -> Unit,
    private val context: Context
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val box: LinearLayout = itemView.movieBox
        val poster: ImageView = itemView.filmPoster
        val title: TextView = itemView.filmTitle
        val rating: TextView = itemView.filmRating

        val view: View = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bundle = Bundle()
        bundle.putSerializable("movie", movieList[position])

        holder.title.text = movieList[position].title
        holder.rating.text = movieList[position].voteAverage.toString()
        holder.box.setOnClickListener { onClick(bundle) }


        val imageUrlResponse = movieList[position].posterPath
        val imageUrl = "https://image.tmdb.org/t/p/original$imageUrlResponse"
        val requestOptions = RequestOptions()

        Glide.with(context).load(imageUrl).apply(requestOptions).skipMemoryCache(true)
            .into(holder.poster)

    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}