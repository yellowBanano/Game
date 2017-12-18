<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 09.12.2017
  Time: 4:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin</title>
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
<section>
    <c:forEach items="${requestScope.accounts}" var="account">
        <ol>
            <li><a href="${pageContext.request.contextPath}/account-info?id=${account.id}"
                   style="color: black; font-family: SansSerif; font-size: larger">${account.email} -- "${account.login}"</a></li>
        </ol>
    </c:forEach>
</section>
</body>
</html>
