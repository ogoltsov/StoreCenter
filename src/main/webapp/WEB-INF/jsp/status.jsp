<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic-page>
    <jsp:useBean id="status" scope="request" class="com.epam.ok.storeCenter.model.Status"/>
    <div class="container">
        <c:if test="${error!=null}">
            <p>${error}</p>
        </c:if>
        <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/app/saveStatus">
            <fieldset>

                <!-- Form Name -->
                <legend>Edit Speciality</legend>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="textinput">ID</label>
                    <div class="col-md-5">
                        <input id="textinput" name="id" type="text" placeholder="ID"
                               class="form-control input-md" readonly value="${status.id}">
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="title">Title</label>
                    <div class="col-md-5">
                        <input id="title" name="title" type="text" placeholder="Speciality Title"
                               class="form-control input-md" required="" value="${status.title}">

                    </div>
                </div>

                <!-- Select Basic -->
                <div class="form-group">
                    <label class="col-md-4 control-label">Delete</label>
                    <div class="col-md-5">
                        <c:choose>
                            <c:when test="${status.deleted}">
                                <p class="form-control-static">True</p>
                            </c:when>
                            <c:otherwise>
                                <p class="form-control-static">False</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <!-- Textarea -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="description">Description</label>
                    <div class="col-md-5">
                        <textarea class="form-control" id="description"
                                  name="description">${status.description}</textarea>
                    </div>
                </div>


                <!-- Button (Double) -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="save">Save</label>
                    <div class="col-md-8">
                        <button id="save" name="save" class="btn btn-success" type="submit">Save</button>
                        <button id="cancel" name="cancel" class="btn btn-danger" type="reset">Cancel</button>
                        <button id="delete" name="delete" class="btn btn-success" type="submit"
                                formaction="${pageContext.request.contextPath}/app/deleteStatus">Delete
                        </button>
                    </div>
                </div>

            </fieldset>
        </form>

    </div>
</t:generic-page>