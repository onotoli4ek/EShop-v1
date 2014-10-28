<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="<%=request.getLocale()%>"/>
<fmt:setLocale value="${pageContext.request.locale}"/>
<fmt:setBundle basename="lang/messages"/>

<html>
<head>
    <title></title>
</head>
<body>
<br/><fmt:message key="hello" />
<br/>
<ul>
    <li> <a href="./B.jsp">B</a></li>
    <li> <a href="./C.jsp">C</a></li>
</ul>


</body>
</html>

