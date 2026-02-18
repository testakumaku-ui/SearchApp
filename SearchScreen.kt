package com.example.booksearchapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.booksearchapp.R
import com.example.booksearchapp.data.Book

@Composable
fun SearchScreen() {

    val viewModel: SearchViewModel = viewModel()
    val state by viewModel.searchState.collectAsState()

    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            "Поиск книг",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null
                )
            },
            label = { Text("Введите название или автора") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { viewModel.search(text) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Найти")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (state) {

            is SearchState.Initial -> {
                CenterText("Введите запрос для поиска")
            }

            is SearchState.Searching -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is SearchState.Success -> {

                val books = (state as SearchState.Success).list

                if (books.isEmpty()) {
                    CenterText("Ничего не найдено")
                } else {
                    LazyColumn {
                        items(books) { book ->
                            BookItem(book)
                            Divider()
                        }
                    }
                }
            }

            is SearchState.Fail -> {
                CenterText("Ошибка", Color.Red)
            }
        }
    }
}

@Composable
fun CenterText(text: String, color: Color = Color.Gray) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text, color = color)
    }
}

@Composable
fun BookItem(book: Book) {
    Column(modifier = Modifier.padding(12.dp)) {
        Text(book.title, fontWeight = FontWeight.Bold)
        Text(book.author)
        Text(book.year)
    }
}