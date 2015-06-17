package ru.ifmo.alekseyivashin.chat_servlet.servlets;

import ru.ifmo.alekseyivashin.chat_servlet.MongoDB;
import ru.ifmo.alekseyivashin.chat_servlet.Record;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alexey on 15.06.2015.
 */
public class Add extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String text = req.getParameter("text");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm', 'EEEE");
        String date = format.format(new Date());
        String login = "";

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("login")) {
                    login = cookie.getValue();
                }
            }
        }

        Record record = new Record(login, text, date);
        MongoDB db = new MongoDB();
        db.addRecord(record);
        //res.sendRedirect("/");
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(record.toJSONString());
    }

}
