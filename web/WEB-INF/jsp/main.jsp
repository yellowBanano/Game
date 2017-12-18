<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 22.11.2017
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="translations"/>
<html lang="${language}">
<head>
    <title>Avatar</title>
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
            top: 60%;
            left: 50%;
            margin-right: -50%;
            transform: translate(-50%, -50%)
        }
    </style>
</head>
<body>
<section>
    <h3><fmt:message key="general"/>:</h3>
    <ul>
        <li><fmt:message key="name"/>: ${requestScope.avatarData.name}</li>
        <li><fmt:message key="class"/>: ${requestScope.avatarData.avatarClassName}</li>
        <li><fmt:message key="level"/>: ${requestScope.avatarData.level}</li>
        <li><fmt:message key="HP"/>: ${requestScope.avatarData.HP}</li>
        <li><fmt:message key="MP"/>: ${requestScope.avatarData.MP}</li>
        <li><fmt:message key="EXP"/>: ${requestScope.avatarData.EXP}</li>
        <li><fmt:message key="money"/>: ${requestScope.avatarData.money}</li>
        <li><fmt:message key="gender"/>: ${requestScope.avatarData.gender}</li>
    </ul>
    <h3><fmt:message key="additionCharacteristics"/>:</h3>
    <ul>
        <li><fmt:message key="strength"/>: ${requestScope.avatarData.STR}</li>
        <li><fmt:message key="defense"/>: ${requestScope.avatarData.DEF}</li>
        <li><fmt:message key="spellPower"/>: ${requestScope.avatarData.spellPower}</li>
        <li><fmt:message key="magicResistance"/>: ${requestScope.avatarData.magicResistance}</li>
        <li><fmt:message key="chanceToHit"/>: ${requestScope.avatarData.hitChance}</li>
        <li><fmt:message key="chanceToDodge"/>: ${requestScope.avatarData.evadeChance}</li>
        <li><fmt:message key="critChance"/>: ${requestScope.avatarData.critChance}</li>
        <li><fmt:message key="critMultiplier"/>: ${requestScope.avatarData.multiplierCrit}</li>
    </ul>
    <h3><fmt:message key="equipment"/>:</h3>
    <ul>
        <li><fmt:message key="weapon"/>: ${requestScope.avatarData.weaponName}</li>
        <li><fmt:message key="armor"/>: ${requestScope.avatarData.armorName}</li>
    </ul>

    <%--Language--%>
    <form>
        <select id="language" name="language" onchange="submit()">
            <option value="en_GB" ${language == 'en_GB' ? 'selected' : ''}>English</option>
            <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Русский</option>
        </select>
    </form>
    <%--Admin--%>
    <a href="${pageContext.request.contextPath}/administrator">
        <button type="button"><fmt:message key="bigBrother"/></button>
    </a>

    <%--Log out--%>
    <form action="${pageContext.request.contextPath}/logout" method="get">
        <button type="submit"><fmt:message key="logOut"/></button>
    </form>

    <%--Download--%>
    <form action="${pageContext.request.contextPath}/report-download" method="get">
        <button type="submit"><fmt:message key="statistics"/></button>
    </form>

    <%--Menu--%>
    <a href="" style="font-style: italic; color: black; text-decoration: underline; font-family: 'Lucida Handwriting'"><fmt:message key="arena"/>(coming
        soon...)</a><br>
    <a href=""
       style="font-style: italic; color: black; text-decoration: underline; font-family: 'Lucida Handwriting'"><fmt:message key="shop"/>(coming soon...)</a><br>
    <a href="${pageContext.request.contextPath}/inventory"
       style="font-style: italic; color: black; text-decoration: underline; font-family: 'Lucida Handwriting'"><fmt:message key="inventory"/></a><br>
    <a href="" style="font-style: italic; color: black; text-decoration: underline; font-family: 'Lucida Handwriting'"><fmt:message key="training"/>(coming
        soon...)</a><br>
    <a href="" style="font-style: italic; color: black; text-decoration: underline; font-family: 'Lucida Handwriting'"><fmt:message key="quests"/>(coming
        soon...)</a><br>
    <a href="${pageContext.request.contextPath}/spells"
       style=" font-style: italic; color: black; text-decoration: underline; font-family: 'Lucida Handwriting'"><fmt:message key="spells"/></a><br>
    <a href="${pageContext.request.contextPath}/notes"
       style="font-style: italic; color: black; text-decoration: underline; font-family: 'Lucida Handwriting'"><fmt:message key="bestiary"/></a><br>
</section>
</body>
</html>
