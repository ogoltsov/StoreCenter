<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic-page>
    <div class="page">
        <p>User:</p>
        <p>Login: ${loggedUser.email}</p>
        <p>Password: ${loggedUser.password}</p>
        <p>Firstname: ${loggedUser.firstname}</p>
        <p>Lastname: ${loggedUser.lastname}</p>
        <p>Role: ${loggedUser.role.toString()}</p>
        <p>Active?: ${!loggedUser.deleted}</p>
    </div>
</t:generic-page>
