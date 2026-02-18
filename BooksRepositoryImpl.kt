package com.example.booksearchapp.data

class BooksRepositoryImpl : BooksRepository {

    private val books = listOf(
        Book("Преступление и наказание", "Фёдор Достоевский", "1866"),
        Book("Война и мир", "Лев Толстой", "1869"),
        Book("Мастер и Маргарита", "Михаил Булгаков", "1967"),
        Book("Анна Каренина", "Лев Толстой", "1877"),
        Book("Отцы и дети", "Иван Тургенев", "1862"),
        Book("Евгений Онегин", "Александр Пушкин", "1833"),
        Book("1984", "Джордж Оруэлл", "1949"),
        Book("Гарри Поттер", "Дж. К. Роулинг", "1997")
    )

    override fun searchBooks(query: String): List<Book> {
        if (query.isBlank()) return emptyList()

        return books.filter {
            it.title.contains(query, true) ||
                    it.author.contains(query, true)
        }
    }
}