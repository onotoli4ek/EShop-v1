<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title></title>
</head>
<body>
<b>index</b>
<%--<fmt:setLocale value="<%=request.getLocale()%>"/>--%>
<%--<fmt:setLocale value="${pageContext.request.locale}"/>--%>
<%--<fmt:setBundle basename="lang/messages"/>--%>
<br/><a href="/product.do?id=1">product</a>
<br/><a href="/productAll.do">productAll</a>

<br/><a href="C.jsp">a.jsp</a>
</body>
</html>
