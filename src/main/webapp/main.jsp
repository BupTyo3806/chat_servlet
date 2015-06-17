<%@ page import="ru.ifmo.alekseyivashin.chat_servlet.Record" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #c8c3c8;
        }

        .form-signin, .form-main, .form-head {
            max-width: 300px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 10px;
            -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            box-shadow: 0 1px 2px rgba(0,0,0,.05);
        }
        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }
        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }
        .form-main, .form-head {
            max-width: none;
            background-color: #ffffff;
        }

    </style>
</head>
<body>
<script>
        $(document).ready(function(){
            $('#btnSubmit').click(function(event){
                var inputText = $('#inputText').val();
                $.post("/add", {text: inputText}, function(resp) {
                    var login = JSON.parse(resp).login;
                    var text = JSON.parse(resp).text;
                    var date = JSON.parse(resp).date;
                    var stringRecord = "<h4>" + login + " &middot " + text + " <small>" + date + "</small>";
                    $('#main-form').append(stringRecord);
                });
            });
        });
</script>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="form-main">
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
                <form class="form-inline" role="form" action="/auth" method="post">
                    <input type="text" class="form-control" placeholder="Username" name="login" style="width: 245px;" required>
                    <input type="password" class="form-control" placeholder="Password" name="password" style="width: 245px;" required>
                    <button type="submit" class="btn btn-default">Sign in</button>
                    <a href="/registration.jsp" class="btn btn-warning">Sign up</a>
                </form>
                <%} else { %>
                <h3>Welcome, <%=login%></h3>
                <a href="/exit" class="btn btn-danger btn-lg">Log out</a>
                <%}%>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="form-main" id="main-form">
                <% if (login != null && login.length() != 0) {%>
                <%@ include file="add.jsp" %>
                <%}%>
                <br>
                <% Record[] records = (Record[]) request.getSession().getAttribute("records");
                    if (records != null) {
                        for (Record record: records) {
                            out.println("<h4>" + record.getLogin() + " &middot " + record.getText() + " <small>" + record.getDate() + "</small>");
                        }
                    }
                %>
            </div>
        </div>
    </div>
</div>
</body>
</html>
