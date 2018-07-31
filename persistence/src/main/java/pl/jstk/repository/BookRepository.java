package pl.jstk.repository;


import java.util.List;

import pl.jstk.entity.BookEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.jstk.enumerations.BookStatus;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("select book from BookEntity book where upper(book.title) like concat('%', upper(:title), '%')")
    List<BookEntity> findBookByTitle(@Param("title") String title);

    @Query("select book from BookEntity book where upper(book.authors) like concat('%', upper(:author), '%')")
    List<BookEntity> findBookByAuthor(@Param("author") String author);

    @Query("select book from BookEntity book where upper(book.authors) like concat('%', upper(:author), '%') " +
            "AND upper(book.title) like concat('%', upper(:title), '%') AND status=:status")
    List<BookEntity> findBookByAuthorTitleStatus(@Param("title") String title, @Param("author") String author,
                                                 @Param("status")BookStatus status);

    @Query("select book from BookEntity book where upper(book.authors) like concat('%', upper(:author), '%') " +
            "AND upper(book.title) like concat('%', upper(:title), '%')")
    List<BookEntity> findBookByAuthorTitle(@Param("title") String title, @Param("author") String author);

}
