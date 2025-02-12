package com.vaniala.movies.ui.screens.search

import androidx.paging.PagingData
import app.cash.turbine.test
import com.vaniala.movies.CoroutinesTestRule
import com.vaniala.movies.TestData.QUERY_EMPTY
import com.vaniala.movies.TestData.QUERY_FOUR_DIGITS
import com.vaniala.movies.TestData.QUERY_THREE_DIGITS
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.usecase.SearchMoviesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    private lateinit var searchMoviesUseCase: SearchMoviesUseCase
    private lateinit var viewModel: SearchViewModel

    @get:Rule
    val coroutineRule = CoroutinesTestRule()

    @Before
    fun setup() {
        searchMoviesUseCase = mockk()
        viewModel = SearchViewModel(searchMoviesUseCase)
    }

    @Test
    fun searchMovies_ReturnEmptyState_WhenQueryIsEmpty() = runTest {
        viewModel.onQueryChange(QUERY_EMPTY)

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(QUERY_EMPTY, state.query)
            assertNull(state.searchResults)
            assertFalse(state.showMinimumCharactersMessage)
        }
    }

    @Test
    fun searchMovies_ShowMinimumCharactersMessage_WhenQueryLessThanFourCharacters() = runTest {
        val pagingData = PagingData.empty<Movie>()
        coEvery { searchMoviesUseCase(QUERY_THREE_DIGITS) } returns flowOf(pagingData)

        viewModel.onQueryChange(QUERY_THREE_DIGITS)

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(QUERY_THREE_DIGITS, state.query)
            assertTrue(state.showMinimumCharactersMessage)
        }
    }

    @Test
    fun searchMovies_SearchMovies_WhenQueryHasFourOrMoreCharacters() = runTest {
        val pagingData = PagingData.from(listOf<Movie>())
        coEvery { searchMoviesUseCase(QUERY_FOUR_DIGITS) } returns flowOf(pagingData)

        viewModel.onQueryChange(QUERY_FOUR_DIGITS)

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(QUERY_FOUR_DIGITS, state.query)
            assertFalse(state.showMinimumCharactersMessage)
        }
    }
}
