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
                <th>Date</th>
                <th>Category</th>
                <th>Status</th>
                <th>Speciality</th>
                <th>Authors</th>
                <c:if test="${loggedUser.role.toString()!='user'}">
                    <th>Action</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${resources}" var="resource">
                <tr>
                    <td></td>
                    <td>${resource.title}</td>
                    <td>${resource.date}</td>
                    <td>${resource.category.title}</td>
                    <td>${resource.status.title}</td>
                    <td>
                        <c:forEach items="${resource.specialities}" var="speciality">
                            <p>${speciality.code}</p>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${resource.authors}" var="author">
                            <p>${author.firstname} ${author.patronymic} ${author.lastname}</p>
                        </c:forEach>
                    </td>
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
                    <td></td>
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
