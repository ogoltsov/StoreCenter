<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic-page>
    <div class="container">
        <table class="table table-hover">
            <thead>
            <tr>
                <th></th>
                <th>Code</th>
                <th>Title</th>
                <c:if test="${loggedUser.role.toString()!='user'}">
                    <th>Action</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${specialityList}" var="speciality">

                <tr>
                    <td></td>
                    <td>${speciality.code}</td>
                    <td>${speciality.title}</td>
                    <c:if test="${loggedUser.role.toString()!='user'}">
                        <td><a href="${pageContext.request.contextPath}/app/speciality?id=${speciality.id}">Edit</a></td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${loggedUser.role.toString()!='user'}">
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><a href="${pageContext.request.contextPath}/app/createSpeciality">Add</a></td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</t:generic-page>