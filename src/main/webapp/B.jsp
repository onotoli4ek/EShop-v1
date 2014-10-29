<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<br/><%=ResourceBundle.getBundle("lang/messages", request.getLocale()).getString("hello")%>
<br/>
<ul>
    <li> <a href="./a.jsp?lang=ru">ru: a.jsp</a></li>
    <li> <a href="./a.jsp?lang=en">en: a.jsp</a></li>
    <li> <a href="./b.jsp?lang=ru">ru: b.jsp</a></li>
    <li> <a href="./b.jsp?lang=en">en: b.jsp</a></li>
</ul>
</body>
</html>
