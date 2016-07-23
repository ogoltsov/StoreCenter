<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap 101 Template</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
</head>
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
    <div><a href="${pageContext.request.contextPath}/app/resource">Resources</a></div>
    <div><a href="#">Authors</a></div>
    <div><a href="#">Status</a></div>
    <div><a href="#">Category</a></div>
    <div><a href="#">College</a></div>
    <div><a href="#">Department</a></div>
    <div><a href="#">Speciality</a></div>
    <div><a href="#">Admin Panel</a></div>
</div>


</body>
</html>