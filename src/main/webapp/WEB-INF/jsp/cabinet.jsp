<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic-page>
    <div class="page">

        <h2 align="center">Welcome to StoreCenter of Education resource </h2>

        <h4>It is your private cabinet</h4>

        <p>You logged as: ${loggedUser.lastname} ${loggedUser.firstname}</p>
        <p>Your email: ${loggedUser.email}</p>
        <p>Your role: ${loggedUser.role.toString()}</p>
    </div>
</t:generic-page>
