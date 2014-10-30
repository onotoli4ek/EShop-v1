<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
REGISTER NEW USER
<form action="/register.do" method="post" enctype="application/x-www-form-urlencoded">
    <br><label for="login">Login:</label>
    <br><input name="login" id="login" type="text" value="${login}">
    ${errorMap.login}

    <br><label for="password">Password:</label>
    <br><input name="password" id="password" type="text" value="${password}">
    ${errorMap.password}

    <br><label for="email">Email:</label>
    <br><input name="email" id="email" type="text" value="${email}">
    ${errorMap.email}

    <br><input type="submit">
</form>
</body>
</html>
