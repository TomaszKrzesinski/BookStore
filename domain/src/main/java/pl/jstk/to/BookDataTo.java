package pl.jstk.to;

import pl.jstk.enumerations.BookStatus;

public class BookDataTo {
    private String title;
    private String authors;
    private BookStatus status;

    public BookDataTo() {
    }

    public BookDataTo(String title, String authors, BookStatus status) {
        this.title = title;
        this.authors = authors;
        this.setStatus(status);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}