package pl.jstk.service.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pl.jstk.entity.BookEntity;
import pl.jstk.enumerations.BookStatus;
import pl.jstk.mapper.BookMapper;
import pl.jstk.repository.BookRepository;
import pl.jstk.service.BookService;
import pl.jstk.to.BookDataTo;
import pl.jstk.to.BookTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookTo> findAllBooks() {
        return BookMapper.map2To(bookRepository.findAll());
    }

    @Override
    public List<BookTo> findBooksByTitle(String title) {
        return BookMapper.map2To(bookRepository.findBookByTitle(title));
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
        return BookMapper.map2To(bookRepository.findBookByAuthor(author));
    }

    @Override
    public List<BookTo> findBookByAuthorTitleStatus(BookTo bookParameters) {
        List<BookTo> listOfBookTO;
        if(bookParameters.getAuthors() == null) bookParameters.setAuthors("");
        if(bookParameters.getTitle() == null) bookParameters.setTitle("");

        try {
            List<BookEntity> listOfBookEntity;
            if(bookParameters.getStatus()==null) {
                 listOfBookEntity = bookRepository.findBookByAuthorTitle(bookParameters.getTitle(), bookParameters.getAuthors());
  //              listOfBookEntity = bookRepository.findBookByTitle(bookParameters.getTitle());
            }
            else {
                listOfBookEntity = bookRepository.findBookByAuthorTitleStatus(bookParameters.getTitle(),
                        bookParameters.getAuthors(), bookParameters.getStatus());
            }
            listOfBookTO = BookMapper.map2To(listOfBookEntity);
        }
        catch(Exception e){
            listOfBookTO = new ArrayList<BookTo>();
        }

        return listOfBookTO;
    }

    @Override
    @Transactional
    public BookTo saveBook(BookTo book) {
        BookEntity entity = BookMapper.map(book);

        entity = bookRepository.save(entity);
        return BookMapper.map(entity);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);

    }

    @Override
    @Transactional
    public BookTo findBookById(Long id){
        Optional<BookEntity> entity = bookRepository.findById(id);
        return BookMapper.map(entity.get());

    }
}
