<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                <%--<jsp:useBean id="resource" scope="request" class="com.epam.ok.storeCenter.model.Resource"/>--%>
                <tr>
                    <td></td>
                    <td>${resource.title}</td>
                    <td>${resource.formatedeDate}</td>
                    <td>${resource.category.title}</td>
                    <td>${resource.status.title}</td>
                    <td>
                        <c:forEach items="${resource.specialities}" var="speciality">
                            <p>${speciality.code}</p>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${resource.authors}" var="author">
                            <p>${author.lastname} ${author.firstname} ${author.patronymic}</p>
                        </c:forEach>
                    </td>
                    <c:if test="${loggedUser.role.toString()!='user'}">
                        <td><a href="${pageContext.request.contextPath}/app/resource?id=${resource.id}">Edit</a></td>
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
                    <td><a href="${pageContext.request.contextPath}/app/createResource">Add</a></td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>

</t:generic-page>
