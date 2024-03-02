
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome To Online Shop</title>
</head>
<body>
<h1 style="text-align: center;">Welcome To Online Shop</h1>
    <br></br>

<form action="/web/user" method="POST" style="text-align: center">

     <input name="name"
     type="text"
     placeholder="Enter your name"/>
    <br><br>
     <input name="password"
      type="text"
      placeholder="Enter your password"/>
      <br><br>
     <input type="checkbox" name="agree" value="true"  />I am agree with the therms of service
     <br><br>
     <input type="submit" value="Enter" />
    </form><
</body>
</html>
