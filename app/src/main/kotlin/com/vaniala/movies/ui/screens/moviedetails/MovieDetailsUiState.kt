package com.vaniala.movies.ui.screens.moviedetails

import com.vaniala.movies.domain.model.MovieAllDetails

data class MovieDetailsUiState(val movieAllDetails: MovieAllDetails? = null, val messageError: String? = null)
