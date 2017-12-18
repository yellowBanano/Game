<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 09.12.2017
  Time: 4:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>
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
    <h2>General:</h2>
    <ul>
        <li>Email: ${requestScope.account.email}</li>
        <li>Login: ${requestScope.account.login}</li>
        <li>Password: ${requestScope.account.password}</li>
        <li>Avatar Name: ${requestScope.account.avatarName}</li>
        <li>Avatar ID: ${requestScope.account.avatarId}</li>
    </ul>
</section>
</body>
</html>
