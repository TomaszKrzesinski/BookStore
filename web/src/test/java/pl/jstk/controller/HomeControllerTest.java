package pl.jstk.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.jstk.constants.ModelConstants;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class HomeControllerTest {
    @SuppressWarnings("unused")
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
