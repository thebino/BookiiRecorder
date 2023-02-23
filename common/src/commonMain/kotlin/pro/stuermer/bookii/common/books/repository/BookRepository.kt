package pro.stuermer.bookii.common.books.repository

import pro.stuermer.bookii.common.books.model.Book

interface BookRepository {
    val books: List<Book>
    fun createBook(book: Book): Boolean
    fun updateBook(book: Book): Boolean
    fun deleteBook(book: Book): Boolean
}
