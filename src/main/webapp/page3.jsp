<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h1 style="text-align: center;">Hello, <%= session.getAttribute("name")%>!</h1>

<h2 style="text-align: center;">You have already chosen:<h2>

 <c:forEach items="${list2}" var="list2">
    <p style="text-align: center;"><c:out value="${list2.value}"/></p>
  </c:forEach>

<jsp:useBean id="productsforshop" class="shop.model.repository.GoodDao" scope="session" />
<jsp:useBean id="products" class="shop.model.repository.OrderGoodDao" scope="session" />

<form action="/web/hello" method="GET" style="text-align: center;">
    <select name="item" >
        <c:forEach items="${list}" var="item">
            <option  value="${item.key}">${item.value}
            </c:forEach>
             </option>
    </select>
    <p>
<input type="submit" name ="submitForm" value="addToCart"  />
<input type="submit" name ="submitForm" value="Submit" />
</form>
<br>
</body>
</html>
