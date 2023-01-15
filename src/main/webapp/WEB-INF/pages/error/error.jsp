<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>Application</title>
  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/auth.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/card.css" type="text/css" rel="stylesheet" media="screen,projection"/>
</head>

<body>
<section id="wrapper" class="error-page">
  <div class="error-box">
    <div class="error-body center-align">
      <h1>${exceptionType}</h1>
      <h3>Service Unavailable!</h3>
      <p class="m-t-30 m-b-30">PLEASE TRY AFTER SOME TIME</p>
      <a href="controller?action=go_to_base_page" class="btn btn-round red waves-effect waves-light m-b-40">Back to home</a>
    </div>
  </div>
</section>

<!--  Scripts-->
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="js/materialize.js"></script>
<script src="js/init.js"></script>
<script src="js/modal.js"></script>
</body>

</html>