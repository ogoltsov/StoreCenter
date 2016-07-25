<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic-page>
    <div class="container">
        <c:if test="${error!=null}">
            <p>${error}</p>
        </c:if>
        <form class="form-horizontal" action="${pageContext.request.contextPath}/app/saveUser" method="post">
            <fieldset>

                <!-- Form Name -->
                <legend>Edit User</legend>
                <jsp:useBean id="user" class="com.epam.ok.storeCenter.model.User" scope="request"/>
                <!-- Text input-->

                <div class="form-group">
                    <label class="col-md-4 control-label" for="id">ID</label>
                    <div class="col-md-5">
                        <input id="id" name="id" type="text" class="form-control input-md"
                               value="${user.id}" readonly="readonly">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="email">Email</label>
                    <div class="col-md-5">
                        <input id="email" name="email" type="text" placeholder="Email" class="form-control input-md"
                               value="${user.email}">
                    </div>
                </div>

                <!-- Password input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="password">Password Input</label>
                    <div class="col-md-5">
                        <input id="password" name="password" type="password" placeholder="Password"
                               class="form-control input-md" required value="${user.password}">

                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="lastname">Lastname</label>
                    <div class="col-md-5">
                        <input id="lastname" name="lastname" type="text" placeholder="Palstname"
                               class="form-control input-md" required value="${user.lastname}">
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="firstname">Firstname</label>
                    <div class="col-md-5">
                        <input id="firstname" name="firstname" type="text" placeholder="Firstname"
                               class="form-control input-md" required value="${user.firstname}">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="isDelete">Deleted</label>
                    <div class="col-md-5">
                        <p class="form-control-static" id="isDelete">${user.deleted}</p>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="role">Role</label>
                    <div class="col-md-5">
                        <select id="role" name="role" class="form-control">
                            <c:choose>
                                <c:when test="${user.role!='admin'}">
                                    <c:forEach items="${roles}" var="role">
                                        <c:choose>
                                            <c:when test="${user.role==role}">
                                                <option value="${role}" selected>${role}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${role}">${role}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <option value="admin">Admin</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Actions</label>
                    <div class="col-md-8">
                        <button type="submit" class="btn btn-success">Save</button>
                        <button type="reset" class="btn btn-warning">Reset</button>
                        <button type="submit" class="btn btn-danger"
                                formaction="${pageContext.request.contextPath}/app/deleteUser">Delete
                        </button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</t:generic-page>