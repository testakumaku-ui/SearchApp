package com.example.booksearchapp.ui

import com.example.booksearchapp.data.Book

sealed class SearchState {

    object Initial : SearchState()

    object Searching : SearchState()

    data class Success(val list: List<Book>) : SearchState()

    data class Fail(val error: String) : SearchState()
}