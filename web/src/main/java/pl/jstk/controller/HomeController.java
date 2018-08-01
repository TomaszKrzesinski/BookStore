package pl.jstk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.jstk.constants.ModelConstants;
import pl.jstk.constants.ViewNames;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    PasswordEncoder passEncoder;

    private static final String INFO_TEXT = "Here You shall display information containing information about newly created TO";
    protected static final String WELCOME = "This is a welcome page";

    @GetMapping(value = "/")
    public String welcome(Model model) {
        model.addAttribute(ModelConstants.MESSAGE, WELCOME);
        model.addAttribute(ModelConstants.INFO, INFO_TEXT);
        System.out.println(passEncoder.encode("admin"));
        System.out.println(passEncoder.encode("tomasz"));
        System.out.println(passEncoder.encode("marek"));
        System.out.println(passEncoder.encode("damian"));
        return ViewNames.WELCOME;
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        return ViewNames.LOGIN;
    }


    @GetMapping(value = "/loginsuccess")
    public String loginSuccess(Model model) {
        return ViewNames.LOGINSUCCESS;
    }

}
