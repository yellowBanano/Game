<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 22.11.2017
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${requestScope.npc.name}</title>
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
        <li>Name: ${requestScope.npc.name}</li>
        <li>Money: ${requestScope.npc.money}</li>
        <li>EXP: ${requestScope.npc.EXP}</li>
        <li>Type: ${requestScope.npc.typeName}</li>
    </ul>
    <h2>Spells:</h2>
    <ul>
        <c:forEach items="${requestScope.spells}" var="spell">
            <li style="font-size: large">name: ${spell.name}</li>
            power: ${spell.power}
            cost: ${spell.cost}
        </c:forEach>
    </ul>
</section>
</body>
</html>
