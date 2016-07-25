<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic-page>
    <div class="container">
        <table class="table table-hover">
            <thead>
            <tr>
                <th></th>
                <th>FIO</th>
                <th>Email</th>
                <th>Role</th>
                <th>Active</th>
                <c:if test="${loggedUser.role.toString()!='user'}">
                    <th>Action</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userList}" var="user">
                <jsp:useBean id="user" scope="request" class="com.epam.ok.storeCenter.model.User" />
                <tr>
                    <td></td>
                    <td>${user.lastname} ${user.firstname}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <td>${!user.deleted}</td>
                    <c:if test="${loggedUser.role.toString()!='user'}">
                        <td><a href="${pageContext.request.contextPath}/app/user?id=${user.id}">Edit</a></td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${loggedUser.role.toString()!='user'}">
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><a href="${pageContext.request.contextPath}/app/createUser">Add</a></td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</t:generic-page>