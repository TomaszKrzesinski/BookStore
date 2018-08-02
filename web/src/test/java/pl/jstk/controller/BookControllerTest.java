package pl.jstk.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.h2.engine.User;
import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.jstk.constants.ModelConstants;
import pl.jstk.enumerations.BookStatus;
import pl.jstk.enumerations.UserRole;
import pl.jstk.service.BookService;
import pl.jstk.to.BookTo;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockHttpServletRequest request;

    @Before
    public void setup() {

    }

    private List<BookTo> generateBooks () {
        List<BookTo> books = new ArrayList<>();

        books.add(new BookTo(1L, "Book1", "Author1", BookStatus.FREE));
        books.add(new BookTo(2L, "Book2", "Author1", BookStatus.FREE));
        books.add(new BookTo(3L, "Book3", "Author2", BookStatus.LOAN));
        books.add(new BookTo(4L, "Album1", "Author2", BookStatus.FREE));
        books.add(new BookTo(5L, "Album2", "Author3", BookStatus.MISSING));

        return books;
    }

    @MockBean
    private BookService bookService;

    @Test
    public void testAdminLogin() throws Exception {
        //given

        //when

        mockMvc.perform(post("/login")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "Tomasz")
                .param("password", "tomasz")
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(redirectedUrl("/loginsuccess"));


    }

    @Test
    public void testBooksPage() throws Exception {
        //given

        List<BookTo> books = generateBooks();

        //when

        when(bookService.findAllBooks()).thenReturn(books);

        HttpSession session = logUser(true);

        mockMvc.perform(get("/books").session((MockHttpSession)session))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.model().attributeExists("bookList"))
                .andExpect(MockMvcResultMatchers.view().name("books"));

        //then
        verify(bookService, times(1)).findAllBooks();
    }

    @Test
    public void testAddBookPage() throws Exception {
        //given


        //when

        HttpSession session = logUser(true);
        //then
        mockMvc.perform(get("/books/add").session((MockHttpSession) session)
                )
                .andExpect(MockMvcResultMatchers.view().name("addbook"))
                .andDo(print())
                .andExpect(model().attributeExists("newBook"));
    }

    @Test
    public void testAddingBook() throws Exception {
        //given

        BookTo newBook = new BookTo();
        newBook.setTitle("test_title");
        newBook.setAuthors("test_authors");
        newBook.setStatus(BookStatus.MISSING);

        //when
        when(bookService.saveBook(newBook)).thenReturn(newBook);

        HttpSession session = logUser(true);

        //then
        mockMvc.perform(post("/greeting").session((MockHttpSession) session).flashAttr("newBook", newBook)
        )
                .andDo(print())
                .andExpect(model().attribute("newBook", newBook))
                .andExpect(view().name("bookadded"));
    }

    @Test
    public void testDeletingBookByAdmin() throws Exception {
        //given

        BookTo removedBook = new BookTo();
        removedBook.setTitle("test_title");
        removedBook.setAuthors("test_authors");
        removedBook.setStatus(BookStatus.MISSING);

        //when
        when(bookService.findBookById(5L)).thenReturn(removedBook);

        HttpSession session = logUser(true);


        //then
        mockMvc.perform(post("/books/delete").session((MockHttpSession)session).param("id", "5")
        )
                .andExpect(MockMvcResultMatchers.view().name("bookremoved"))
                .andDo(print())
                .andExpect(model().attribute("book", removedBook));
    }

    @Test
    public void testDeletingBookByUser() throws Exception {
        //given

        BookTo removedBook = new BookTo();
        removedBook.setTitle("test_title");
        removedBook.setAuthors("test_authors");
        removedBook.setStatus(BookStatus.MISSING);

        //when
        when(bookService.findBookById(5L)).thenReturn(removedBook);

        HttpSession session = logUser(false);

        //then
        mockMvc.perform(post("/books/delete").session((MockHttpSession)session).param("id", "5")
        )
                .andExpect(MockMvcResultMatchers.view().name("403"));
    }



    private HttpSession logUser(boolean admin) throws  Exception{
        String username = "";
        String password = "";
        if(admin) {
            username = "Tomasz";
            password = "tomasz";
        } else {
            username = "Damian";
            password = "damian";
        }


        return mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", username)
                .param("password", password)
        )
                .andReturn()
                .getRequest()
                .getSession();
    }

}

