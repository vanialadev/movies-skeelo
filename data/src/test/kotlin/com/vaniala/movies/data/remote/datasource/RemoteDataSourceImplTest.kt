package com.vaniala.movies.data.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.cash.turbine.test
import com.vaniala.movies.data.TestData
import com.vaniala.movies.data.TestData.moviePopularResponse
import com.vaniala.movies.data.mappers.Mappers.toRequest
import com.vaniala.movies.data.remote.model.MovieResponse
import com.vaniala.movies.data.remote.paging.MoviePaging
import com.vaniala.movies.data.remote.service.MovieService
import com.vaniala.movies.domain.model.AddFavorite
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class RemoteDataSourceImplTest {

    private lateinit var movieService: MovieService
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var pagingSource: MoviePaging

    @Before
    fun setup() {
        movieService = mockk()
        remoteDataSource = RemoteDataSourceImpl(movieService)
        pagingSource = MoviePaging(movieService)
    }

    @Test
    fun getMovieDetails_Emit_Success() = runTest {
        coEvery { movieService.getMovieDetails(any()) } returns TestData.movieDetailsResponse

        remoteDataSource.getMovieDetails(1).test {
            val result = awaitItem()
            assertEquals(TestData.movieDetailsResponse.id, result.id)
            assertEquals(TestData.movieDetailsResponse.title, result.title)
            assertEquals(TestData.movieDetailsResponse.overview, result.overview)
            assertEquals(TestData.movieDetailsResponse.voteAverage, result.voteAverage)
            awaitComplete()
        }

        coVerify { movieService.getMovieDetails(1) }
    }

    @Test
    fun getMovieDetails_Emit_Error() = runTest {
        val exception = RuntimeException("error")
        coEvery { movieService.getMovieDetails(any()) } throws exception

        val result = remoteDataSource.getMovieDetails(1).catch {
            assertEquals(exception.message, it.message)
        }.toList()

        assert(result.isEmpty())
    }

    @Test
    fun getMoviePopular_Return_Success() = runTest {
        coEvery { movieService.getMoviePopular(1) } returns moviePopularResponse

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 20,
                placeholdersEnabled = false,
            ),
        )

        assert(result is PagingSource.LoadResult.Page)
        result as PagingSource.LoadResult.Page<Int, MovieResponse>
        assertEquals(null, result.prevKey)
        assertEquals(2, result.nextKey)
        assertEquals(moviePopularResponse.results, result.data)
    }

    @Test
    fun getMoviePopular_HasNextKey_Correct() = runTest {
        val currentPage = 2
        coEvery { movieService.getMoviePopular(currentPage) } returns moviePopularResponse

        val result = pagingSource.load(
            PagingSource.LoadParams.Append(
                key = currentPage,
                loadSize = 20,
                placeholdersEnabled = false,
            ),
        )

        assert(result is PagingSource.LoadResult.Page)
        result as PagingSource.LoadResult.Page<Int, MovieResponse>
        assertEquals(null, result.prevKey)
        assertEquals(currentPage + 1, result.nextKey)
        assertEquals(moviePopularResponse.results, result.data)

        assertEquals(null, result.prevKey)
        assertEquals(3, result.nextKey)
        assertEquals(moviePopularResponse.results, result.data)
    }

    @Test
    fun getMoviePopular_HasPrevKey_Correct() = runTest {
        val currentPage = 2
        coEvery { movieService.getMoviePopular(currentPage) } returns moviePopularResponse

        val result = pagingSource.load(
            PagingSource.LoadParams.Prepend(
                key = currentPage,
                loadSize = 20,
                placeholdersEnabled = false,
            ),
        )

        assert(result is PagingSource.LoadResult.Page)
        result as PagingSource.LoadResult.Page<Int, MovieResponse>
        assertEquals(null, result.prevKey)
        assertEquals(currentPage + 1, result.nextKey)
        assertEquals(moviePopularResponse.results, result.data)
    }

    @Test
    fun getMoviePopular_Return_Error() = runTest {
        val exception = RuntimeException("error")
        coEvery { movieService.getMoviePopular(any()) } throws exception

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 20,
                placeholdersEnabled = false,
            ),
        )

        assert(result is PagingSource.LoadResult.Error)
        result as PagingSource.LoadResult.Error
        assertEquals(exception, result.throwable)
    }

    @Test
    fun getMoviePopular_Return_PageEmpty() = runTest {
        coEvery { movieService.getMoviePopular(any()) } returns TestData.moviePopularEmptyResponse

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 20,
                placeholdersEnabled = false,
            ),
        )

        assert(result is PagingSource.LoadResult.Page)
        result as PagingSource.LoadResult.Page<Int, MovieResponse>
        assert(result.data.isEmpty())
        assertEquals(null, result.prevKey)
        assertEquals(null, result.nextKey)
    }

    @Test
    fun getMoviePopular_Return_RefreshKeyCorrects() = runTest {
        val state = mockk<PagingState<Int, MovieResponse>>()
        coEvery { state.anchorPosition } returns 20
        coEvery { state.closestPageToPosition(20) } returns mockk {
            every { prevKey } returns 1
            every { nextKey } returns 3
        }

        val result = pagingSource.getRefreshKey(state)
        assertEquals(2, result)
    }

    @Test
    fun getMoviePopular_Emit_Success() = runTest {
        coEvery { movieService.getMoviePopular(any()) } returns moviePopularResponse

        remoteDataSource.getMoviePopular().test {
            val pagingData = awaitItem()
            assertNotNull(pagingData)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getMoviePopular_Emit_Success_And_WaitComplete() = runTest {
        coEvery { movieService.getMoviePopular(any()) } returns moviePopularResponse

        remoteDataSource.getMoviePopular()
            .take(1)
            .test(timeout = 5.seconds) {
                val pagingData = awaitItem()
                assertNotNull(pagingData)
                awaitComplete()
            }
    }

    @Test
    fun getMoviePopular_Emit_Error() = runTest {
        val exception = RuntimeException("error")
        coEvery { movieService.getMoviePopular(any()) } throws exception

        remoteDataSource.getMoviePopular()
            .catch { error ->
                assertEquals(exception.message, error.message)
            }
            .test {
                cancelAndIgnoreRemainingEvents()
            }
    }

    @Test
    fun addFavorites_Emit_AddWatchListOrFavorite() = runTest {
        val favorite = AddFavorite(mediaId = 1, favorite = true)
        val response = TestData.addWatchListOrFavoriteResponse
        coEvery {
            movieService.addFavorite(
                accountId = any(),
                sessionId = any(),
                body = any(),
            )
        } returns response

        remoteDataSource.addFavorites(favorite).test {
            val result = awaitItem()
            assertEquals(response.success, result.success)
            assertEquals(response.statusMessage, result.statusMessage)
            awaitComplete()
        }

        coVerify {
            movieService.addFavorite(
                accountId = any(),
                sessionId = any(),
                body = favorite.toRequest(),
            )
        }
    }

    @Test
    fun addFavorites_Emit_Error() = runTest {
        val favorite = AddFavorite(mediaId = 1, favorite = true)
        val exception = RuntimeException("error")
        coEvery {
            movieService.addFavorite(
                accountId = any(),
                sessionId = any(),
                body = any(),
            )
        } throws exception

        remoteDataSource.addFavorites(favorite).test {
            val result = awaitItem()
            assertEquals(false, result.success)
            assertEquals(exception.message, result.statusMessage)
            awaitComplete()
        }
    }
}
