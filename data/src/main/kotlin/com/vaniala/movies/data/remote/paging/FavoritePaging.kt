package com.vaniala.movies.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vaniala.movies.data.BuildConfig
import com.vaniala.movies.data.remote.model.MovieResponse
import com.vaniala.movies.data.remote.service.MovieService
import com.vaniala.movies.data.utils.Constants.STARTING_PAGE_INDEX
import timber.log.Timber

class FavoritePaging(private val service: MovieService) : PagingSource<Int, MovieResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val accountId = BuildConfig.ACOUNT_ID
        val sessionId = BuildConfig.SESSION_ID

        return try {
            val response = service.getFavorites(accountId = accountId, sessionId = sessionId, page = position)
            val movies = response.results
            val totalPages = response.totalPages ?: STARTING_PAGE_INDEX
            val nextKey = if (position >= totalPages.toInt()) null else position.plus(1)

            Timber.d("$nextKey")
            LoadResult.Page(
                data = movies ?: emptyList(),
                prevKey = null,
                nextKey = nextKey,
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? = state.anchorPosition?.let { anchorPosition ->
        state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
            ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
    }
}
