<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic-page>
    <jsp:useBean id="speciality" scope="request" class="com.epam.ok.storeCenter.model.Speciality"/>
    <div class="container">
        <c:if test="${deleteError!=null}">
            <p>${deleteError}</p>
        </c:if>
        <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/app/saveSpeciality">
            <fieldset>
                <legend>Edit Speciality</legend>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="textinput">ID</label>
                    <div class="col-md-5">
                        <input id="textinput" name="id" type="text" placeholder="ID"
                               class="form-control input-md" value="${speciality.id}" readonly>

                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="title">Title</label>
                    <div class="col-md-5">
                        <input id="title" name="title" type="text" placeholder="Speciality Title"
                               class="form-control input-md" required="" value="${speciality.title}">

                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="code">Code</label>
                    <div class="col-md-5">
                        <input id="code" name="code" type="text" placeholder="Code" class="form-control input-md"
                               required="" value="${speciality.code}">

                    </div>
                </div>

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

                <div class="form-group">
                    <label class="col-md-4 control-label" for="save">Save</label>
                    <div class="col-md-8">
                        <button id="save" name="save" class="btn btn-success" type="submit">Save</button>
                        <button id="cancel" name="cancel" class="btn btn-warning" type="reset">Cancel</button>
                        <button id="delete" name="delete" class="btn btn-danger" type="submit"
                                formaction="${pageContext.request.contextPath}/app/deleteSpeciality">Delete
                        </button>
                    </div>
                </div>

            </fieldset>
        </form>

    </div>
</t:generic-page>