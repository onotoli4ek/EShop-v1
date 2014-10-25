<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="entity.Product" %>
<html>
    <head>
        <title></title>
    </head>
    <body>
        <b>PRODUCT PAGE</b>
        <br/>id: ${product.id} <%--EL=Express language--%>
        <br/>id: <%= ((Product)request.getAttribute("product")).getId()%> <%--Scriplet--%>
       <%-- <br/>id:
        <% response.getWriter().write(((Product)request.getAttribute("product")).getId());
        %>--%>
        <br/>name: ${product.name}
        <br/><a href="/index.jsp">main page</a>

        <br/> <%--Add quiz to bucket--%>
        <a href="./productAddToBucket.do?id=${product.id}">Add this product to bucket</a>
        <hr/> <%--Show products brucket--%>
        <h2>Products in bucket</h2>
        <ul>
            <c:forEach var="productInBucket" items="${productsInBucket}">
                <li>
                        <a href="/product.do?id=${productInBucket.key.id}">${productInBucket.key.name}</a>:=
                    ${productInBucket.value}
                </li>
            </c:forEach>
        </ul>
    </body>
</html>
