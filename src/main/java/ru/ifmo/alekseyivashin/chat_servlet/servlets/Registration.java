package ru.ifmo.alekseyivashin.chat_servlet.servlets;

import ru.ifmo.alekseyivashin.chat_servlet.MongoDB;
import ru.ifmo.alekseyivashin.chat_servlet.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Alexey on 14.06.2015.
 */
public class Registration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        MongoDB db = new MongoDB();
        User adduser = new User(req.getParameter("login"), req.getParameter("password"));
        if (db.findUser(adduser.getLogin()) != null) {
            out.println("����� ��� ����������!");
            req.getRequestDispatcher("registration.jsp").forward(req, res);
        } else {
            db.addUser(adduser);
            res.sendRedirect("/");
        }
    }
}
