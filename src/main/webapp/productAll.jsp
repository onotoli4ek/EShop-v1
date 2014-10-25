<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
<b>ALL PRODUCT PAGE</b>

<br/>productList.list[0] = ${productList[1]}
<ul>
    <li><a href="/product.do?id=2">Paper</a> </li>
    <li><a href="/product.do?id=1">Bread</a> </li>
    <li><a href="/product.do?id=3">Sugar</a> </li>
</ul>
<hr/> <%--Show products brucket--%>
<h4>Products in bucket</h4>
<br/>requestAttribute.list[0] = ${productList[0].name}
<ul>
    <c:forEach var="productL" items="${productList}">
        <li>
            <%--<a href="/product.do?id=${productInBucket.key.id}">${productInBucket.key.name}</a>:=--%>
                ${productL.name}
        </li>
    </c:forEach>
</ul>
</body>

</body>
</html>
