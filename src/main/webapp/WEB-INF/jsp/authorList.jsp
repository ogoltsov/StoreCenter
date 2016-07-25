<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic-page>
    <div class="container">
        <table class="table table-hover">
            <thead>
            <tr>
                <th></th>
                <th>Lastname</th>
                <th>Firstname</th>
                <th>Patronymic</th>
                <c:if test="${loggedUser.role.toString()!='user'}">
                    <th>Action</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${authorList}" var="author">
                <tr>
                    <td></td>
                    <td>${author.lastname}</td>
                    <td>${author.firstname}</td>
                    <td>${author.patronymic}</td>
                    <c:if test="${loggedUser.role.toString()!='user'}">
                        <td><a href="${pageContext.request.contextPath}/app/author?id=${author.id}">Edit</a></td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${loggedUser.role.toString()!='user'}">
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><a href="${pageContext.request.contextPath}/app/createAuthor">Add</a></td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>

</t:generic-page>
