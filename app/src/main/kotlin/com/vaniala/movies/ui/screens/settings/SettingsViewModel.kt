package com.vaniala.movies.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaniala.movies.preferences.ThemePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(private val themePreferences: ThemePreferences) : ViewModel() {

    val isDarkTheme: StateFlow<Boolean> = themePreferences.isDarkTheme
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false,
        )

    fun setDarkTheme(isDark: Boolean) {
        viewModelScope.launch {
            themePreferences.setDarkTheme(isDark)
        }
    }
}
