<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 08.12.2017
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign Up</title>
    <style>
        body {
            background: #F2994A; /* fallback for old browsers */
            background: -webkit-linear-gradient(to right, #F2C94C, #F2994A);
            background: linear-gradient(to right, #F2C94C, #F2994A);
            color: black;
        }

        section {
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

<form action="${pageContext.request.contextPath}/registration" method="post">
    <section>
        <h2>Email</h2>
        <input type="text" id="email" name="email"><br>
        <h2>Login</h2>
        <input type="text" id="login" name="login"><br>
        <h2>Password</h2>
        <input type="password" id="password" name="password"><br><br>
        <br>
        <h1 style="font-style: italic; font-family: 'Microsoft Sans Serif'">Avatar</h1>
        <input type="text" name="avatarName" placeholder="Avatar nickname"><br>
        <input type="radio" value="MALE" name="gender" checked>Мужчина
        <input type="radio" value="FEMALE" name="gender">Женщина<br>
        <select name="chosenClass">
            <c:forEach items="${requestScope.allClasses}" var="aClass">
                <option value="${aClass.id}">${aClass.name}</option>
            </c:forEach>
        </select>
        <br><br>

        <button type="submit" style="background-color: lightgreen">Save</button>
        <br><br>
    </section>
    <h3>Warnings:</h3>
    <c:forEach var="message" items="${requestScope.getOrDefault('messages', '')}">
        <c:out value="${message}"/><br>
    </c:forEach>
</form>
</body>
</html>
