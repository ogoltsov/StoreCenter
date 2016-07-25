<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic-page>
    <div class="container">
        <table class="table table-hover">
            <thead>
            <tr>
                <th></th>
                <th>Title</th>
                <th>Description</th>
                <c:if test="${loggedUser.role.toString()!='user'}">
                    <th>Action</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${statusList}" var="status">
                <jsp:useBean id="status" scope="request" class="com.epam.ok.storeCenter.model.Status"/>
                <tr>
                    <td></td>
                    <td>${status.title}</td>
                    <td>${status.description}</td>
                    <c:if test="${loggedUser.role.toString()!='user'}">
                        <td><a href="${pageContext.request.contextPath}/app/status?id=${status.id}">Edit</a></td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${loggedUser.role.toString()!='user'}">
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><a href="${pageContext.request.contextPath}/app/createStatus">Add</a></td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</t:generic-page>