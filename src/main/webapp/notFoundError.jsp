<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>Error :(</h1>

<c:if test="${requestScope.error ne null }">
    <c:out value="${requestScope.error}" ></c:out>
</c:if>

</body>
</html>
