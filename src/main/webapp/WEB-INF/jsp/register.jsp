<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Login Form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<section class="container">
    <div class="login">
        <h1>Register to Web App</h1>
        <c:if test="${error!=null}">
            <p style="color: darkred">${error}</p>
        </c:if>
        <c:if test="${emailError!=null}">
            <p style="color: darkred">${emailError}</p>
        </c:if>
        <form method="post" action="${pageContext.request.contextPath}/app/register">
            <p><input type="text" name="email" required placeholder="Email" value="${userData.email}"></p>
            <p><input type="password" name="password" required placeholder="Password" value="${userData.password}"></p>
            <p><input type="text" name="lastname" required placeholder="Lastname" value="${userData.lastname}"></p>
            <p><input type="text" name="firstname" required placeholder="Firstname" value="${userData.firstname}"></p>

            <p class="submit"><input type="submit" name="commit" value="Register"></p>
        </form>
    </div>
</section>
</body>
</html>





