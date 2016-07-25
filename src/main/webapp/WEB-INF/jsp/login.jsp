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
        <h1>Login to Web App</h1>
        <c:if test="${loginError !=null}">
            <p>${loginError}</p>
        </c:if>
        <form method="post" action="${pageContext.request.contextPath}/app/login">
            <p><input type="text" name="email" value="k.ogoltsov@gmail.com" placeholder="Username or Email"></p>
            <p><input type="password" name="password" value="Administrator" placeholder="Password"></p>
            <p class="submit"><input type="submit" name="commit" value="Login"></p>
        </form>
    </div>

    <div class="login-help">
        <p><a href="${pageContext.request.contextPath}/app/register">Click here to register</a>.</p>
    </div>
</section>
</body>
</html>
