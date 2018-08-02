package pl.jstk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.jstk.constants.ModelConstants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class HomeControllerTest {
    @Autowired
    public PasswordEncoder encoder;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
    }

    @Test
    public void testHomePage() throws Exception {
        // given when
        ResultActions resultActions = mockMvc.perform(get("/"));
        // then
        resultActions.andExpect(status().isOk())
                     .andExpect(view().name("welcome"))
                     .andDo(print())
                     .andExpect(model().attribute(ModelConstants.MESSAGE, HomeController.WELCOME))
                     .andExpect(content().string(containsString("")));

    }

    @Test
    public void testLoginPage() throws Exception {
        // given
        ResultActions resultActions = mockMvc.perform(get("/login"));
        // then
        resultActions.andExpect(status().isOk())
                .andExpect(view().name("login"));
    }


    @Test
    public void testLoginSuccessPage() throws Exception {
        // given
        ResultActions resultActions = mockMvc.perform(get("/loginsuccess"));
        // then
        resultActions.andExpect(status().isOk())
                .andExpect(view().name("loginsuccess"));
    }

}
