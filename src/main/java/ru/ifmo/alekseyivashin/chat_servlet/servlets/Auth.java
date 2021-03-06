package ru.ifmo.alekseyivashin.chat_servlet.servlets;

import ru.ifmo.alekseyivashin.chat_servlet.MongoDB;
import ru.ifmo.alekseyivashin.chat_servlet.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Alexey on 14.06.2015.
 */
public class Auth extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        MongoDB db = new MongoDB();
        User user = new User(req.getParameter("login"), req.getParameter("password"));
        User testuser = db.findUser(user.getLogin());
        if (testuser != null) {
            if (user.getPassword().equals(testuser.getPassword())) {
                Cookie loginCookie = new Cookie("login", user.getLogin());
                loginCookie.setMaxAge(30*60);
                res.addCookie(loginCookie);
                res.sendRedirect("/");
            } else {
                res.sendRedirect("/");
            }
        } else {
            res.sendRedirect("/");
        }
    }
}
