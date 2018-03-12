package ru.javawebinar.topjava.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.AuthorizedUser;

import javax.servlet.http.HttpServletRequest;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
@Controller
public class RootController {

    @GetMapping(value = "/")
    public String root() {
        return "redirect:meals";
    }

    @GetMapping(value = "/users")
    public String users() {
        return "users";
    }

    @PostMapping(value = "/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        return "redirect:meals";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/meals")
    public String meals() {
        return "meals";
    }
}
