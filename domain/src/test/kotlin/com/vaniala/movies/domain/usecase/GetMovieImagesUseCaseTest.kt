package com.vaniala.movies.domain.usecase

import app.cash.turbine.test
import com.vaniala.movies.domain.TestData.MOVIE_ID
import com.vaniala.movies.domain.TestData.exception
import com.vaniala.movies.domain.TestData.image
import com.vaniala.movies.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetMovieImagesUseCaseTest {

    private lateinit var repository: MovieRepository
    private lateinit var useCase: GetMovieImagesUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetMovieImagesUseCase(repository)
    }

    @Test
    fun invoke_ReturnImage_Success() = runTest {
        coEvery { repository.getMovieImages(any()) } returns flowOf(image)

        useCase(MOVIE_ID).test {
            val result = awaitItem()
            assertEquals(image.id, result.id)
            assertEquals(image.posters, result.posters)
            awaitComplete()
        }

        coVerify { repository.getMovieImages(MOVIE_ID) }
    }

    @Test
    fun invoke_ReturnImage_Error() = runTest {
        coEvery { repository.getMovieImages(any()) } returns flow { throw exception }

        useCase(MOVIE_ID).test {
            val error = awaitError()
            assertEquals(exception.message, error.message)
        }
    }
}
