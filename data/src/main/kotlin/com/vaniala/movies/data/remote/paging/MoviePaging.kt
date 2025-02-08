package com.vaniala.movies.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vaniala.movies.data.remote.model.MovieResponse
import com.vaniala.movies.data.remote.service.MovieService
import retrofit2.HttpException
import timber.log.Timber

private const val STARTING_PAGE_INDEX = 1

class MoviePaging(private val service: MovieService) : PagingSource<Int, MovieResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = service.getMoviePopular(position)
            val movies = response.results
            val totalPages: Long = response.totalPages ?: 1
            val nextKey = if (position.toLong() == totalPages) null else position + 1

            Timber.tag("akuaku").d("$nextKey")
            LoadResult.Page(
                data = movies ?: emptyList(),
                prevKey = null,
                nextKey = nextKey,
            )
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? = state.anchorPosition?.let { anchorPosition ->
        state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
            ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
    }
}
