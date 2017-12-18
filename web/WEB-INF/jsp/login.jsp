<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 08.12.2017
  Time: 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            background: #F2994A; /* fallback for old browsers */
            background: -webkit-linear-gradient(to right, #F2C94C, #F2994A);
            background: linear-gradient(to right, #F2C94C, #F2994A);
            color: black;
        }

        section {
            background: black;
            color: white;
            border-radius: 1em;
            padding: 1em;
            position: absolute;
            top: 50%;
            left: 50%;
            margin-right: -50%;
            transform: translate(-50%, -50%)
        }
    </style>
</head>
<body>
<section>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <input type="text" name="email" id="email" placeholder="Email"/><br/>
        <input type="password" name="password" id="password" placeholder="Password"/><br/>
        <button type="submit">Log In</button>
    </form>
<a href="${pageContext.request.contextPath}/registration">
    <button type="button">Sign Up</button>
</a>
</section>
<br><br>
<h3>Warnings:</h3>
<c:forEach var="message" items="${requestScope.getOrDefault('messages', '')}">
    <c:out value="${message}"/><br>
</c:forEach>
</body>
</html>
