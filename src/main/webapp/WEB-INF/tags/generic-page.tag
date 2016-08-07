<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<fmt:bundle basename="i18n">
    <fmt:message key="logout" var="logout"/>
    <fmt:message key="menu.profile" var="profile"/>
    <fmt:message key="menu.resources" var="resources"/>
    <fmt:message key="menu.authors" var="authors"/>
    <fmt:message key="menu.statuses" var="statuses"/>
    <fmt:message key="menu.categories" var="categories"/>
    <fmt:message key="menu.specialities" var="specialities"/>
    <fmt:message key="menu.users" var="users"/>
    <fmt:message key="modal.title.selectAuthors" var="selectAuthors"/>
    <fmt:message key="modal.button.save" var="save"/>
    <fmt:message key="modal.button.close" var="close"/>
</fmt:bundle>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap 101 Template</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">
</head>
<script src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<body>
<div class="topMenu">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-3 col-sm-3 col-md-3 col-lg-2 col-xs-offset-9 col-sm-offset-9 col-md-offset-9 col-lg-offset-10">
                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="langSelector">

                        <div class="form-group">

                            <form action="${pageContext.request.contextPath}/app/locale" method="get" >
                                <select name="locale" id="locale" class="form-control" onchange="this.form.submit">
                                    <option value="ru">Russian</option>
                                    <option value="en">English</option>
                                </select>
                            </form>

                            <%--<select class="form-control" id="sel1">--%>
                                <%--<option><a href="${pageContext.request.contextPath}/app/locale?locale=ru">russian</a>--%>
                                <%--</option>--%>
                                <%--<a href="${pageContext.request.contextPath}/app/locale?locale=en">--%>
                                    <%--<option value=""></option>--%>
                                <%--</a>--%>
                                <%--<option><a href="${pageContext.request.contextPath}/app/locale?locale=en">english</a>--%>
                                <%--</option>--%>
                            <%--</select>--%>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <div class="logout">
                        <a href="${pageContext.request.contextPath}/app/logout">${logout}</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="menu">
    <div class="userinfo"></div>
    <div><a href="${pageContext.request.contextPath}/app/cabinet">${profile}</a></div>
    <div><a href="${pageContext.request.contextPath}/app/resource">${resources}</a></div>
    <div><a href="${pageContext.request.contextPath}/app/author">${authors}</a></div>
    <div><a href="${pageContext.request.contextPath}/app/status">${statuses}</a></div>
    <div><a href="${pageContext.request.contextPath}/app/category">${categories}</a></div>
    <div><a href="${pageContext.request.contextPath}/app/speciality">${specialities}</a></div>
    <c:if test="${loggedUser.role.toString()=='admin'}">
        <div><a href="${pageContext.request.contextPath}/app/user">${users}</a></div>
    </c:if>
    <div><a href="${pageContext.request.contextPath}/app/locale?locale=en">Set Lang: English</a></div>
    <div><a href="${pageContext.request.contextPath}/app/locale?locale=ru">Set Lang: Russian</a></div>
</div>
<div class="workspace">
    <jsp:doBody/>
</div>

<div class="modal fade" id="author-modal">
    <div class="modal-dailog modal-md-5">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal">
                    &times;
                </button>
                <h4 class="modal-title">${selectAuthors}:</h4>

            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/app/setAuthorForResource" method="post">
                    <input type="text" hidden value="${resource.id}" name="resourceId">
                    <select id="author" name="authorId" class="form-control">
                        <c:forEach var="author" items="${authorList}">
                            <jsp:useBean id="author" scope="request" class="com.epam.ok.storeCenter.model.Author"/>
                            <option value="${author.id}">${author.lastname} ${author.firstname} ${author.patronymic}</option>
                        </c:forEach>
                    </select>
                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-success">${save}</button>
                <button class="btn btn-danger" type="button" data-dismiss="modal">${close}</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="speciality-modal">
    <div class="modal-dailog modal-md-5">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal">
                    &times;
                </button>
                <h4 class="modal-title">Select authors:</h4>

            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/app/setSpecialityForResource" method="post">
                    <input type="text" hidden value="${resource.id}" name="resourceId">
                    <select id="speciality" name="specId" class="form-control">
                        <c:forEach var="speciality" items="${specialityList}">
                            <option value="${speciality.id}">${speciality.code} - ${speciality.title}</option>
                        </c:forEach>
                    </select>
                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-success">Save</button>
                <button class="btn btn-danger" type="button" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


</body>
</html>