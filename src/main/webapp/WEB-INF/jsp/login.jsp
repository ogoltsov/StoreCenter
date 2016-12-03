<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="i18n">
    <fmt:message key="email" var="email"/>
    <fmt:message key="password" var="password"/>
    <fmt:message key="login.title" var="loginTitle"/>
    <fmt:message key="login.registerLink" var="registerLink"/>
    <fmt:message key="login.button.login" var="loginButton"/>
    <fmt:message key="login.error" var="error"/>
</fmt:bundle>

<html>
<head>
    <meta charset="utf-8">
    <title>Login Form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<section class="container">
    <div class="login">
        <h1>${loginTitle}</h1>
        <c:if test="${loginError !=null}">
            <p class="loginError">${error}</p>
        </c:if>
        <form method="post" action="${pageContext.request.contextPath}/app/login">
            <p><input type="text" name="email" value="" placeholder="${email}"></p>
            <p><input type="password" name="password" value="" placeholder="${password}"></p>
            <p class="submit"><input type="submit" name="commit" value="${loginButton}"></p>
        </form>
    </div>

    <div class="login-help">
        <p><a href="${pageContext.request.contextPath}/app/register">${registerLink}</a>.</p>
        <p><a href="/app/locale"></a></p>
    </div>
</section>
</body>
</html>
