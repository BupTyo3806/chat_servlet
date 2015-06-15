<%@ page import="ru.ifmo.alekseyivashin.chat_servlet.Record" %>
<%@ page import="java.io.PrintWriter" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 14.06.2015
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>
</head>
<body>
<h1>Главная страница</h1>
<div style="border: 3px;">
  <% String login = "";
      Cookie[] cookies = request.getCookies();
      if (cookies != null) {
          for (Cookie cookie: cookies) {
              if (cookie.getName().equals("login")) {
                  login = cookie.getValue();
              }
          }
      }
    if (login == null || login.length() == 0) {%>
  <form action="/auth" method="post">
    <input type="text" placeholder="Логин" name="login" required>
    <input type="password" placeholder="Пароль" name="password" required>
    <button type="submit">Вход</button>
    <a href="registration.jsp">Регистрация</a>
  </form>
  <%} else { %>
  <h3>Здравствуйте, <%=login%></h3><br>
    <a href="/exit">Выход</a>
  <%}%>
</div> <br>
<% if (login != null && login.length() != 0) {%>
<%@ include file="add.jsp" %>
<%}%>
<br>
<% Record[] records = (Record[]) request.getSession().getAttribute("records");
    if (records != null) {
        for (Record record: records) {
            out.println(record.getLogin() + ": " + record.getText() + " &middot " + record.getDate());
        }
    }
%>
</body>
</html>
