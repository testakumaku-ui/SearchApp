package com.example.booksearchapp.data

interface BooksRepository {
    fun searchBooks(query: String): List<Book>
}