<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <div class="col-md-1 col-md-offset-11">
                <div class="logout"><a href="${pageContext.request.contextPath}/app/logout">LogOut</a></div>
            </div>
        </div>
    </div>
</div>

<div class="menu">
    <div class="userinfo"></div>
    <div><a href="${pageContext.request.contextPath}/app/cabinet">Profile</a></div>
    <div><a href="${pageContext.request.contextPath}/app/resource">Resources</a></div>
    <div><a href="${pageContext.request.contextPath}/app/author">Authors</a></div>
    <div><a href="${pageContext.request.contextPath}/app/status">Status</a></div>
    <div><a href="${pageContext.request.contextPath}/app/category">Category</a></div>
    <div><a href="${pageContext.request.contextPath}/app/speciality">Speciality</a></div>
    <c:if test="${loggedUser.role.toString()=='admin'}">
        <div><a href="${pageContext.request.contextPath}/app/user">Users</a></div>
    </c:if>
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
                <h4 class="modal-title">Select authors:</h4>

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

            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-success">Save</button>
                </form>
                <button class="btn btn-danger" type="button" data-dismiss="modal">Close</button>
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

            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-success">Save</button>
                </form>
                <button class="btn btn-danger" type="button" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


</body>
</html>