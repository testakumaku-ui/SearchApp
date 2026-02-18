package com.example.booksearchapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksearchapp.data.BooksRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val repository = BooksRepositoryImpl()

    private val _searchState = MutableStateFlow<SearchState>(SearchState.Initial)
    val searchState = _searchState.asStateFlow()

    fun search(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _searchState.value = SearchState.Searching
                delay(500)
                val result = repository.searchBooks(query)
                _searchState.value = SearchState.Success(result)
            } catch (e: Exception) {
                _searchState.value = SearchState.Fail("Ошибка поиска")
            }
        }
    }
}