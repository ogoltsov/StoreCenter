
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/app/register">
    <c:if test="${error!=null}">
        <p style="color: darkred">${error}</p>
    </c:if>
    <label for="email">Email</label>
    <input type="text" id="email" name="email" placeholder="Email" value="${userData.email}">
    <c:if test="${emailError!=null}">
        <p style="color: darkred">${emailError}</p>
    </c:if>
    <br>
    <label for="password">Password</label>
    <input type="password" id="password" name="password" placeholder="Password" value="${userData.password}">
    <br>
    <label for="firstname">Firstname</label>
    <input type="text" id="firstname" name="firstname" placeholder="Firstname" value="${userData.firstname}">
    <br>
    <label for="lastname">Lastname</label>
    <input type="text" id="lastname" name="lastname" placeholder="Lastname" value="${userData.lastname}">
    <br>
    <button type="submit">Register</button>
    <button type="reset">Reset</button>
</form>
</body>
</html>
