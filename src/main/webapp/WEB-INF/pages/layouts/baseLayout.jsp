<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ include file="/WEB-INF/pages/tiles/body/localMessages.jsp" %>
<c:if test="${sessionScope.language eq null}">
    <c:set var="language" value="en_US" scope="session"/>
</c:if>

<!DOCTYPE html>

<html lang="${sessionScope.language}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>News</title>
    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/auth.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/card.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/photogrid.css" type="text/css" rel="stylesheet" media="screen,projection"/>
</head>
<body class="white" lang="${sessionScope.language}">

<div class="main-wrapper">
    <c:import url="/WEB-INF/pages/tiles/header.jsp"/>
</div>

<div class="container-fluid">
    <c:import url="/WEB-INF/pages/tiles/iconCard.jsp"/>
</div>

<nav>
    <c:import url="/WEB-INF/pages/tiles/navigationBar.jsp"/>
</nav>

<ul id="slide-out" class="sidenav">
    <c:import url="/WEB-INF/pages/tiles/sidenav/sideMenu.jsp"/>
</ul>

<div class="row" lang="${sessionScope.language}">

    <div class="col s12 content center-align">
        <c:import url="/WEB-INF/pages/tiles/body/body.jsp"/>
    </div>
</div>

<br> <br>
<br> <br>
<br> <br>
<br> <br>

<footer class="page-footer black">
    <c:import url="/WEB-INF/pages/tiles/body/footer.jsp"/>
</footer>

<!--  Scripts-->
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
<script src="js/materialize.js"></script>
<script src="js/init.js"></script>
<script src="js/modal.js"></script>
<script src="js/toast.js"></script>
<script src="js/realTime.js"></script>
<script src="js/dateTime.js"></script>
<script src="js/tiny.js"></script>
<script src="js/preview.js"></script>
</body>
</html>