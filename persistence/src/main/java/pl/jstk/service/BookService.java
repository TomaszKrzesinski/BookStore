package pl.jstk.service;

import java.util.List;

import pl.jstk.enumerations.BookStatus;
import pl.jstk.to.BookDataTo;
import pl.jstk.to.BookTo;

public interface BookService {

    List<BookTo> findAllBooks();
    BookTo findBookById(Long id);
    List<BookTo> findBooksByTitle(String title);
    List<BookTo> findBooksByAuthor(String author);
    List<BookTo> findBookByAuthorTitleStatus(BookTo bookParameters);

    BookTo saveBook(BookTo book);
    void deleteBook(Long id);
}
