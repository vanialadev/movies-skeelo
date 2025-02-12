package com.vaniala.movies.domain.usecase

import androidx.paging.PagingData
import app.cash.turbine.test
import com.vaniala.movies.domain.TestData.QUERY_EMPTY
import com.vaniala.movies.domain.TestData.QUERY_FOUR_DIGITS
import com.vaniala.movies.domain.TestData.QUERY_THREE_DIGITS
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SearchMoviesUseCaseTest {

    private lateinit var repository: MovieRepository
    private lateinit var useCase: SearchMoviesUseCase

    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        useCase = SearchMoviesUseCase(repository)
    }

    @Test
    fun searchMovies_ReturnEmptyPagingData_WhenQueryLessThanFourCharacters() = runTest {
        useCase(QUERY_THREE_DIGITS).test {
            awaitItem()
            awaitComplete()
        }
        verify(exactly = 0) { repository.searchMovies(any()) }
    }

    @Test
    fun invoke_ReturnPagingData_WhenQueryHasFourOrMoreCharacters() = runTest {
        val pagingData = PagingData.from(listOf<Movie>())
        coEvery { repository.searchMovies(QUERY_FOUR_DIGITS) } returns flowOf(pagingData)

        useCase(QUERY_FOUR_DIGITS).test {
            awaitItem()
            awaitComplete()
        }

        coVerify(exactly = 1) { repository.searchMovies(QUERY_FOUR_DIGITS) }
    }

    @Test
    fun invoke_ReturnEmptyPagingData_WhenQueryIsEmpty() = runTest {
        useCase(QUERY_EMPTY).test {
            awaitItem()
            awaitComplete()
        }

        verify(exactly = 0) { repository.searchMovies(any()) }
    }
}
