<%--
  Created by IntelliJ IDEA.
  User: Alexey
  Date: 14.06.2015
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="border: 3px;">
  <% String login = request.getParameter("login");
    if (login == null || login.length() == 0) {%>
      <form action="/auth" method="post">
        <input type="text" placeholder="Логин" name="login" required>
        <input type="password" placeholder="Пароль" name="password" required>
        <button type="submit">Вход</button>
        <a href="/registration">Регистрация</a>
      </form>
    <%} else { %>
      <h3>Здравствуйте, <%login%></h3>
    <%}
  %>
</div>
