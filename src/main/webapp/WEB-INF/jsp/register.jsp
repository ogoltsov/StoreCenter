<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="i18n">
    <fmt:message key="email" var="email"/>
    <fmt:message key="password" var="password"/>
    <fmt:message key="register.title" var="registerTtile"/>
    <fmt:message key="register.login.error" var="loginError"/>
    <fmt:message key="register.login.error.email" var="loginEmailError"/>
    <fmt:message key="lastname" var="lastname"/>
    <fmt:message key="firstname" var="firstname"/>
    <fmt:message key="register.button.title" var="registerButton"/>

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
        <h1>${registerTtile}</h1>
        <c:if test="${error!=null}">
            <p style="color: darkred">${loginError}</p>
        </c:if>
        <c:if test="${emailError!=null}">
            <p style="color: darkred">${loginEmailError}</p>
        </c:if>
        <form method="post" action="${pageContext.request.contextPath}/app/register">
            <p><input type="text" name="email" required placeholder="${email}" value="${userData.email}"></p>
            <p><input type="password" name="password" required placeholder="${password}" value="${userData.password}"></p>
            <p><input type="text" name="lastname" required placeholder="${lastname}" value="${userData.lastname}"></p>
            <p><input type="text" name="firstname" required placeholder="${firstname}" value="${userData.firstname}"></p>

            <p class="submit"><input type="submit" name="commit" value="${registerButton}"></p>
        </form>
    </div>
</section>
</body>
</html>





