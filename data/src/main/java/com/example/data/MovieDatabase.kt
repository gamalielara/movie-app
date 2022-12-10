package com.example.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_list")
data class MovieDatabase(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name="movie_id")
    var movieId: String = "",

    @ColumnInfo(name="title")
    var title: String = "",

    @ColumnInfo(name="is_liked")
    var isLiked: Boolean = false,
)