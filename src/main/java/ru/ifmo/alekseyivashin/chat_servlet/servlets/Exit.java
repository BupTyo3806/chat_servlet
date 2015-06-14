package ru.ifmo.alekseyivashin.chat_servlet.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexey on 14.06.2015.
 */
public class Exit extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("login")) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    res.addCookie(cookie);
                }
            }
        }
        res.sendRedirect("/");
    }
}
