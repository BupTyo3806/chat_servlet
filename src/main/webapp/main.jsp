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
    <title></title>
</head>
<body>
<%@ include file="auth.jsp" %>
<% if (login != null && login.length() != 0) {
<%@ include file="add.jsp" %>
}%>
</body>
</html>
