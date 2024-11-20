package com.example.csvexample

import android.content.Context
import com.example.csvexample.Book
import java.io.BufferedReader
import java.io.InputStreamReader

object CsvReader {

    fun readBooksFromCsv(context: Context, fileName: String): List<Book> {
        val books = mutableListOf<Book>()
        try {
            val inputStream = context.assets.open(fileName)
            val reader = BufferedReader(InputStreamReader(inputStream))

            reader.useLines { lines ->
                lines.drop(1).forEach { line ->
                    val parts = line.split(",")
                    if (parts.size == 5) {
                        books.add(
                            Book(
                                title = parts[0],
                                author = parts[1],
                                pages = parts[2].toInt(),
                                price = parts[3].toDouble(),
                                publicationYear = parts[4].toInt()
                            )
                        )
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return books
    }
}