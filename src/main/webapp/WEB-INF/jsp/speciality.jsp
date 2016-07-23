<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic-page>
    <jsp:useBean id="speciality" scope="request" class="com.epam.ok.storeCenter.model.Speciality"/>
    <div class="container">
        <form class="form-horizontal">
            <fieldset>

                <!-- Form Name -->
                <legend>Edit Speciality</legend>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="textinput">ID</label>
                    <div class="col-md-5">
                        <input id="textinput" name="textinput" type="text" placeholder="ID"
                               class="form-control input-md" required="" value="${speciality.id}">

                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="title">Title</label>
                    <div class="col-md-5">
                        <input id="title" name="title" type="text" placeholder="Speciality Title"
                               class="form-control input-md" required="" value="${speciality.title}">

                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="code">Code</label>
                    <div class="col-md-5">
                        <input id="code" name="code" type="text" placeholder="Code" class="form-control input-md"
                               required="" value="${speciality.code}">

                    </div>
                </div>

                <!-- Select Basic -->
                <div class="form-group">
                    <label class="col-md-4 control-label">Delete</label>
                    <div class="col-md-5">
                            <c:choose>
                                <c:when test="${speciality.deleted}">
                                    <p class="form-control-static">True</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="form-control-static">False</p>
                                </c:otherwise>
                            </c:choose>
                    </div>
                </div>

                <!-- Button (Double) -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="save">Save</label>
                    <div class="col-md-8">
                        <button id="save" name="save" class="btn btn-success">Save</button>
                        <button id="cancel" name="cancel" class="btn btn-danger">Cancel</button>
                    </div>
                </div>

            </fieldset>
        </form>

    </div>
</t:generic-page>