<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>

<html lang="${sessionScope.language}">

<div class="container">
    <div class="row">
        <div class="col l6 s12">
            <h5 class="white-text">24 <fmt:message key="label.news"/></h5>
        </div>
        <div class="col l4 offset-l2 s12">
            <h5 class="white-text"><fmt:message key="label.links"/></h5>
            <ul>
                <li><a class="grey-text text-lighten-3" href="#!"><fmt:message key="label.link"/> 1</a></li>
                <li><a class="grey-text text-lighten-3" href="#!"><fmt:message key="label.link"/> 2</a></li>
                <li><a class="grey-text text-lighten-3" href="#!"><fmt:message key="label.link"/> 3</a></li>
                <li><a class="grey-text text-lighten-3" href="#!"><fmt:message key="label.link"/> 4</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="footer-copyright">
    <div class="container">
        © 2022 24 <fmt:message key="label.news"/>
        <a class="grey-text text-lighten-4 right" href="#!"><fmt:message key="label.moreLinks"/></a>
    </div>
</div>
</html>