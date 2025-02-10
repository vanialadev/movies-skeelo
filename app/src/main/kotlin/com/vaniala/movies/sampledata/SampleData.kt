package com.vaniala.movies.sampledata

import androidx.paging.PagingData
import com.vaniala.movies.domain.model.Image
import com.vaniala.movies.domain.model.Movie
import kotlinx.coroutines.flow.flowOf

val sampleListMovie = listOf(
    Movie(
        backdropPath = "/backdrop1.jpg",
        overview = "This is the overview for Movie One.",
        video = false,
        images = Image(id = 1, "/image1.jpg"),
        title = "Movie One",
        id = 1,
    ),
    Movie(
        backdropPath = "/backdrop2.jpg",
        overview = "This is the overview for Movie Two.",
        video = true,
        images = Image(id = 2, "/image2.jpg"),
        title = "Movie Two",
        id = 2,
    ),
    Movie(
        backdropPath = "/backdrop3.jpg",
        overview = "This is the overview for Movie Three.",
        video = false,
        images = Image(id = 3, "/image3.jpg"),
        title = "Movie Three",
        id = 3,
    ),
    Movie(
        backdropPath = "/backdrop4.jpg",
        overview = "This is the overview for Movie Four.",
        video = false,
        images = Image(id = 4, "/image4.jpg"),
        title = "Movie Four",
        id = 4,
    ),
    Movie(
        backdropPath = "/backdrop5.jpg",
        overview = "This is the overview for Movie Five.",
        video = true,
        images = Image(id = 5, "/image5.jpg"),
        title = "Movie Five",
        id = 5,
    ),
)

val sampleMoviePageData = flowOf(PagingData.from(sampleListMovie))
