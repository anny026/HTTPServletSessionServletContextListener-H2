<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>

<html>
<body>
<form action="/web/user" method="GET" style="text-align: center;">
<h1 style="text-align: center;">Hello, <%= session.getAttribute("name")%>!</h1>
</form>

<h2 style="text-align: center;">Make you order<h2>

<jsp:useBean id="productsforshop" class="shop.model.repository.GoodDao" scope="session" />


<form action="/web/hello" method="GET" style="text-align: center;">
    <select name="item" >
        <c:forEach items="${list}" var="item">
            <option  value="${item.key}">${item.value}
            </c:forEach>
             </option>
    </select>
    <br>
<input type="submit" name ="submitForm" value="addToCart"  />
<input type="submit" name ="submitForm" value="Submit" />

<c:forEach items="${requestScope.list}" var="list" >
  <p style="text-align: center;"><c:out value="${list}"/></p>
  </c:forEach>
</form>

</body>
</html>
