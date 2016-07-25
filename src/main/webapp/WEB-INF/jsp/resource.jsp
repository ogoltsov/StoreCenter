<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:generic-page>
    <div class="container">
        <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/app/saveResource">
            <fieldset>
                <form class="form-horizontal">
                    <fieldset>

                        <!-- Form Name -->
                        <legend>Edit Resource</legend>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="id">ID</label>
                            <div class="col-md-5">
                                <input id="id" name="id" type="text" placeholder="" class="form-control input-md"
                                       value="${resource.id}">

                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="title">Title</label>
                            <div class="col-md-8">
                                <input id="title" name="title" type="text" placeholder="Resource Title"
                                       class="form-control input-md" required="" value="${resource.title}">
                            </div>
                        </div>
                        <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
                        <script type="text/javascript"
                                src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
                        <link rel="stylesheet"
                              href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

                        <script>
                            $(document).ready(function () {
                                var date_input = $('input[name="date"]'); //our date input has the name "date"
                                var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
                                date_input.datepicker({
                                    format: 'yyyy-mm-dd',
                                    container: container,
                                    todayHighlight: true,
                                    autoclose: true
                                })
                            })
                        </script>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="date">Title</label>
                            <div class="col-md-8">
                                <input id="date" name="date" type="text" placeholder="Resource Title"
                                       class="form-control input-md" value="${resource.formatedeDate}">
                            </div>
                        </div>

                        <!-- Select Basic -->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="status">Status</label>
                            <div class="col-md-5">
                                <select id="status" name="status" class="form-control">
                                    <c:forEach var="status" items="${statusList}">
                                        <c:choose>
                                            <c:when test="${status.id==resource.status.id}">
                                                <option value="${status.id}" selected>${status.title}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${status.id}">${status.title}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <!-- Select Basic -->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="category">Category</label>
                            <div class="col-md-5">
                                <select id="category" name="category" class="form-control">
                                    <c:forEach var="category" items="${categoryList}">
                                        <c:choose>
                                            <c:when test="${category.id==resource.category.id}">
                                                <option value="${category.id}" selected>${category.title}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${category.id}">${category.title}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label" for="save">Actions</label>
                            <div class="col-md-8">
                                <button id="save" name="save" class="btn btn-success" type="submit">Save</button>
                                <button id="reset" name="reset" class="btn btn-warning" type="reset">Reset</button>
                                <button id="delete" name="delete" class="btn btn-danger" type="submit" formmethod="post"
                                        formaction="${pageContext.request.contextPath}/app/deleteResource">
                                    Delete
                                </button>
                            </div>
                        </div>

                    </fieldset>
                </form>
            </fieldset>
        </form>


        <div class="container">
            <h4>Authors:</h4>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th></th>
                    <th>Lastname</th>
                    <th>Firstname</th>
                    <th>Patronymic</th>
                    <c:if test="${loggedUser.role.toString()!='user'}">
                        <th>Action</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${resource.authors}" var="author">
                    <tr>
                        <td></td>
                        <td>${author.lastname}</td>
                        <td>${author.firstname}</td>
                        <td>${author.patronymic}</td>
                        <c:if test="${loggedUser.role.toString()!='user'}">
                            <td><a href="${pageContext.request.contextPath}/app/removeAuthorFromResource?resId=${resource.id}&authorId=${author.id}" >Remove</a></td>
                        </c:if>
                    </tr>
                </c:forEach>
                <c:if test="${loggedUser.role.toString()!='user'}">
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><a data-toggle="modal" data-target="#author-modal">Add</a></td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
<script>
    function myFunction(x, y) {


    }
</script>
        <div class="container">
            <h4>Specialities:</h4>
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
                <c:forEach items="${resource.specialities}" var="speciality">

                    <tr>
                        <td></td>
                        <td>${speciality.code}</td>
                        <td>${speciality.title}</td>
                        <c:if test="${loggedUser.role.toString()!='user'}">
                            <td><a href="${pageContext.request.contextPath}/app/removeSpecialityFromResource?resId=${resource.id}&specId=${speciality.id}">Remove</a></td>
                        </c:if>
                    </tr>
                </c:forEach>
                <c:if test="${loggedUser.role.toString()!='user'}">
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><a data-toggle="modal" data-target="#speciality-modal">Add</a></td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>

</t:generic-page>