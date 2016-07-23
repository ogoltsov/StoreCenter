<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic-page>
    <div class="container">
        <form class="form-horizontal">
            <fieldset>

                <!-- Form Name -->
                <legend>Edit User</legend>
                <jsp:useBean id="user" class="com.epam.ok.storeCenter.model.User" scope="request"/>
                <!-- Text input-->
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

                <!-- Select Basic -->

                <div class="form-group">
                    <label class="col-md-4 control-label" for="selectbasic">Role</label>
                    <div class="col-md-5">
                        <select id="selectbasic" name="selectbasic" class="form-control">
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


                <!-- Multiple Checkboxes -->
                <c:if test="${user.role.toString()!='admin'}">
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="checkboxes">Active</label>
                        <div class="col-md-4">
                            <div class="checkbox" id="checkboxes">
                                <label for="checkboxes-0">
                                    <c:choose>
                                        <c:when test="${!user.deleted}">
                                            <input type="checkbox" name="checkboxes" id="checkboxes-0" checked>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" name="checkboxes" id="checkboxes-0">
                                        </c:otherwise>
                                    </c:choose>
                                </label>
                            </div>
                        </div>
                    </div>

                </c:if>

                <!-- Button (Double) -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="buttonSave">Actions</label>
                    <div class="col-md-8">
                        <button id="buttonSave" name="buttonSave" type="submit" class="btn btn-success">Save</button>
                        <button id="buttonCancel" name="buttonCancel" type="reset" class="btn btn-danger">Reset</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</t:generic-page>