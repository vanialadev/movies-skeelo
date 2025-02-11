package com.vaniala.movies.data.repository

import androidx.paging.PagingData
import app.cash.turbine.test
import com.vaniala.movies.data.TestData
import com.vaniala.movies.data.TestData.listMovie
import com.vaniala.movies.data.TestData.movieDetails
import com.vaniala.movies.data.mappers.Mappers.toModel
import com.vaniala.movies.data.remote.datasource.RemoteDataSource
import com.vaniala.movies.domain.model.AddFavorite
import com.vaniala.movies.domain.model.MovieDetails
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieRepositoryImplTest {

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var repository: MovieRepositoryImpl

    @Before
    fun setup() {
        remoteDataSource = mockk()
        repository = MovieRepositoryImpl(remoteDataSource)
    }

    @Test
    fun getMovieDetails_ReturnMovieDetails_Success() = runTest {
        val expectedMovieDetails = MovieDetails(
            id = 1,
            title = "Teste Movie",
            overview = "Overview do filme teste",
            voteAverage = 8.5,
            posterPath = "/poster.jpg",
            backdropPath = "/backdrop.jpg",
        )
        coEvery { remoteDataSource.getMovieDetails(any()) } returns flowOf(expectedMovieDetails)

        val result = repository.getMovieDetails(1).first()

        assertEquals(expectedMovieDetails.id, result.id)
        assertEquals(expectedMovieDetails.title, result.title)
        assertEquals(expectedMovieDetails.overview, result.overview)
        assertEquals(expectedMovieDetails.voteAverage, result.voteAverage)
        coVerify { remoteDataSource.getMovieDetails(1) }
    }

    @Test
    fun getMovieDetails_Emit_Error() = runTest {
        val exception = RuntimeException("Erro ao buscar detalhes do filme")
        coEvery { remoteDataSource.getMovieDetails(any()) } throws exception

        try {
            repository.getMovieDetails(1).first()
            assert(false) { "Deveria ter lançado uma exceção" }
        } catch (e: Exception) {
            assertEquals(exception.message, e.message)
        }
    }

    @Test
    fun getMoviePopular_ReturnPagingData_Success() = runTest {
        val pagingData = PagingData.from(listMovie)
        every { remoteDataSource.getMoviePopular() } returns flowOf(pagingData)

        val result = repository.getMoviePopular().first()

        assertNotNull(result)
        coVerify { remoteDataSource.getMoviePopular() }
    }

    @Test
    fun getMoviePopular_Return_Error() = runTest {
        val exception = RuntimeException("error")
        every { remoteDataSource.getMoviePopular() } throws exception

        try {
            repository.getMoviePopular().first()
            assert(false) { "exception" }
        } catch (e: Exception) {
            assertEquals(exception.message, e.message)
        }
    }

    @Test
    fun getMovieDetails_Emit_Success() = runTest {
        coEvery { remoteDataSource.getMovieDetails(any()) } returns flowOf(movieDetails)

        repository.getMovieDetails(1).test {
            val result = awaitItem()
            assertEquals(movieDetails.id, result.id)
            assertEquals(movieDetails.title, result.title)
            assertEquals(movieDetails.overview, result.overview)
            awaitComplete()
        }

        coVerify { remoteDataSource.getMovieDetails(1) }
    }

    @Test
    fun getMoviePopular_Emit_Success() = runTest {
        val pagingData = PagingData.from(listMovie)
        coEvery { remoteDataSource.getMoviePopular() } returns flowOf(pagingData)

        repository.getMoviePopular()
            .take(1)
            .test(timeout = 5.seconds) {
                val result = awaitItem()
                assertNotNull(result)
                awaitComplete()
            }

        coVerify { remoteDataSource.getMoviePopular() }
    }

    @Test
    fun addFavorites_Emit_Success() = runTest {
        val favorite = AddFavorite(mediaId = 1, favorite = true)
        coEvery { remoteDataSource.addFavorites(any()) } returns flowOf(TestData.addWatchListOrFavoriteResponse.toModel())

        repository.addFavorites(favorite).test {
            val result = awaitItem()
            assertEquals(TestData.addWatchListOrFavoriteResponse.success, result.success)
            assertEquals(
                TestData.addWatchListOrFavoriteResponse.statusMessage,
                result.statusMessage,
            )
            awaitComplete()
        }

        coVerify { remoteDataSource.addFavorites(favorite) }
    }

    @Test
    fun addFavorites_Emit_Error() = runTest {
        val favorite = AddFavorite(mediaId = 1, favorite = true)
        val exception = RuntimeException("error")
        coEvery { remoteDataSource.addFavorites(any()) } returns flowOf(
            TestData.addWatchListOrFavoriteResponse.copy(
                success = false,
                statusMessage = exception.message,
            ).toModel(),
        )

        repository.addFavorites(favorite).test {
            val result = awaitItem()
            assertEquals(false, result.success)
            assertEquals(exception.message, result.statusMessage)
            awaitComplete()
        }
    }
}
