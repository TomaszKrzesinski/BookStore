package pl.jstk.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.jstk.constants.ModelConstants;
import pl.jstk.constants.ViewNames;
import pl.jstk.enumerations.BookStatus;
import pl.jstk.service.BookService;
import pl.jstk.to.BookDataTo;
import pl.jstk.to.BookTo;

import java.util.List;

@Controller
public class BooksController {
    private static final String INFO_TEXT = "Manage your books.";
    protected static final String WELCOME = "This is page with books.";

    private BookService bookService;

    @Autowired
    public BooksController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping(value = "/books")
    public String books(Model model) {
        List<BookTo> listOfBooks = bookService.findAllBooks();
        model.addAttribute("bookList", listOfBooks);
        return ViewNames.BOOKS;
    }

    @GetMapping(value = "/books/book")
    public String book(@RequestParam("id") Long id, Model model) {
        BookTo book = bookService.findBookById(id);
        model.addAttribute("book", book);
        return ViewNames.BOOK;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(value = "/books/delete")
    public String removeBook(@RequestParam("id") Long id, Model model) {
        BookTo book = bookService.findBookById(id);
        model.addAttribute("book", book);
        bookService.deleteBook(id);
        return ViewNames.BOOKREMOVED;
    }

    @GetMapping(value = "/books/add")
    public String addBook(Model model) {

        BookTo newBook = new BookTo();

        newBook.setTitle("Title");
        newBook.setAuthors("Authors");
        newBook.setStatus(BookStatus.FREE);
        model.addAttribute("newBook", newBook);

        return ViewNames.ADDBOOK;
    }

    @PostMapping(value = "/greeting")
    public String addBookToDB(@ModelAttribute("newBook") BookTo newBook, Model model) {
        BookTo savedBook = bookService.saveBook(newBook);
        model.addAttribute("book", savedBook);
        return ViewNames.BOOKADDED;
    }

    @GetMapping(value = "/books/search")
    public String searchBooks(Model model) {
        BookTo book = new BookTo();
        book.setTitle("Title");
        book.setAuthors("Authors");

        model.addAttribute("findBook", book);
        return ViewNames.SEARCHBOOK;
    }

    @GetMapping(value = "/books/searchresults")
    public String displaySearchResults(@ModelAttribute("findBook") BookTo findBook, Model model) {
        System.out.println(findBook.getAuthors());

        List<BookTo> listOfBooks = bookService.findBookByAuthorTitleStatus(findBook);
        model.addAttribute("bookList", listOfBooks);
        model.addAttribute("findBook", findBook);
        return ViewNames.SEARCHRESULTS;
    }

    @ExceptionHandler({AccessDeniedException.class})
    public String accessDeniedHandler(Model model) {
        model.addAttribute("error", "You shall not pass!");
        return ViewNames.ACCESSDENIED;
    }
}
