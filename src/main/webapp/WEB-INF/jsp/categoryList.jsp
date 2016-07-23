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
            <c:forEach items="${categoryList}" var="categoty">
                <tr>
                    <td></td>
                    <td>${categoty.title}</td>
                    <td>${categoty.description}</td>
                    <c:if test="${loggedUser.role.toString()!='user'}">
                        <td><a href="#">Edit</a></td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${loggedUser.role.toString()!='user'}">
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><a href="#">Add</a></td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</t:generic-page>