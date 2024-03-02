<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h1 style="text-align: center;">Dear <%= session.getAttribute("name")%>, your order:</h1>

<h2 style="text-align: center;">You have already chosen:<h2>
<c:forEach items="${list2}" var="list2">
    <p style="text-align: center;"><c:out value="${list2.value}"/></p>
  </c:forEach>
<br>
<p style="text-align: center;">Total: <%= session.getAttribute("list4") %> </p>
</body>
</html>
