package com.vaniala.movies

fun soma(a: Int, b: Int): Int = a + b

// @HiltViewModel
// class MoviesViewModel @Inject constructor(private val dataStore: DataStore<Preferences>) : ViewModel() {
//    private val _isLoading = MutableStateFlow(true)
//    val isLoading: StateFlow<Boolean> = _isLoading
//
//    init {
//        viewModelScope.launch {
// //            delay(500)
//            _isLoading.value = false
//        }
//    }

//    suspend fun saveDarkMode(isDarkTheme: Boolean) {
//        dataStore.edit { preferences ->
//            preferences[DARK_MODE_KEY] = isDarkTheme
//        }
//    }
//
//    private suspend fun getDarkMode(): Boolean = dataStore.data.map { preferences ->
//        preferences[DARK_MODE_KEY] ?: false
//    }.first()
// }
