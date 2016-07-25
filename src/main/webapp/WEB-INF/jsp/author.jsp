<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic-page>
    <jsp:useBean id="author" scope="request" class="com.epam.ok.storeCenter.model.Author"/>
    <div class="container">
        <c:if test="${error!=null}">
            <p>${error}</p>
        </c:if>

        <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/app/saveAuthor">
            <fieldset>

                <legend>Edit Speciality</legend>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="textinput">ID</label>
                    <div class="col-md-5">
                        <input id="textinput" name="id" type="text" placeholder="ID"
                               class="form-control input-md" readonly value="${author.id}">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="lastname">Lastname</label>
                    <div class="col-md-5">
                        <input id="lastname" name="lastname" type="text" placeholder="Lastname"
                               class="form-control input-md" required="" value="${author.lastname}">
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-md-4 control-label" for="firstname">Firstname</label>
                    <div class="col-md-5">
                        <input id="firstname" name="firstname" type="text" placeholder="Speciality Title"
                               class="form-control input-md" required="" value="${author.firstname}">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="patronymic">Patronymic</label>
                    <div class="col-md-5">
                        <input id="patronymic" name="patronymic" type="text" placeholder="Speciality Title"
                               class="form-control input-md" required="" value="${author.patronymic}">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="save">Save</label>
                    <div class="col-md-8">
                        <button id="save" name="save" class="btn btn-success" type="submit">Save</button>
                        <button id="cancel" name="cancel" class="btn btn-danger" type="reset">Cancel</button>
                        <button id="delete" name="delete" class="btn btn-success" type="submit"
                                formaction="${pageContext.request.contextPath}/app/deleteAuthor">Delete
                        </button>
                    </div>
                </div>
            </fieldset>
        </form>

        <c:if test="${author.id!=null}">
            <div class="container">
                <h4>Resources</h4>

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
                                    <p>${author.lastname} ${author.firstname} ${author.patronymic}</p>
                                </c:forEach>
                            </td>
                            <c:if test="${loggedUser.role.toString()!='user'}">
                                <td><a href="${pageContext.request.contextPath}/app/resource?id=${resource.id}">Edit</a>
                                </td>
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
        </c:if>

    </div>
</t:generic-page>