package com.vaniala.movies.domain.usecase

import app.cash.turbine.test
import com.vaniala.movies.domain.TestData.pagingData
import com.vaniala.movies.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class GetMoviePopularUseCaseTest {

    private lateinit var repository: MovieRepository
    private lateinit var useCase: GetMoviePopularUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetMoviePopularUseCase(repository)
    }

    @Test
    fun invoke_ReturnPagingDataMovie_Success() = runTest {
        coEvery { repository.getMoviePopular() } returns flowOf(pagingData)

        useCase()
            .take(1)
            .test(timeout = 5.seconds) {
                val result = awaitItem()
                assertNotNull(result)
                awaitComplete()
            }

        coVerify { repository.getMoviePopular() }
    }

    @Test
    fun invoke_ReturnPagingDataMovie_Error() = runTest {
        coEvery { repository.getMoviePopular() } returns flowOf(pagingData)

        useCase()
            .take(1)
            .test(timeout = 5.seconds) {
                val result = awaitItem()
                assertNotNull(result)
                awaitComplete()
            }
    }
}
