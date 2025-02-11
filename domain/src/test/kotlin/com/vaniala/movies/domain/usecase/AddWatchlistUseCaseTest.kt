package com.vaniala.movies.domain.usecase

import app.cash.turbine.test
import com.vaniala.movies.domain.TestData.response
import com.vaniala.movies.domain.TestData.watchlist
import com.vaniala.movies.domain.model.AddWatchlist
import com.vaniala.movies.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AddWatchlistUseCaseTest {

    private lateinit var repository: MovieRepository
    private lateinit var useCase: AddWatchlistUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = AddWatchlistUseCase(repository)
    }

    @Test
    fun invoke_ReturnAddWatchListOrFavorite_Success() = runTest {
        coEvery { repository.addWatchlist(any()) } returns flowOf(response)

        useCase(watchlist).test {
            val result = awaitItem()
            assertEquals(response.success, result.success)
            assertEquals(response.statusMessage, result.statusMessage)
            awaitComplete()
        }

        coVerify { repository.addWatchlist(AddWatchlist()) }
    }

    @Test
    fun invoke_ReturnAddWatchListOrFavorite_Error() = runTest {
        coEvery { repository.addWatchlist(any()) } returns flowOf(response)

        useCase(watchlist).test {
            val result = awaitItem()
            assertEquals(response.success, result.success)
            assertEquals(response.statusMessage, result.statusMessage)
            awaitComplete()
        }
    }
}
