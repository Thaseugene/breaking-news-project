<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>

<html lang="${sessionScope.language}">
<div class="row black">
    <div class="col s11">
        <nav class="z-depth-0">
            <div class="nav-wrapper black">
                <a href="controller?action=go_to_news_list" class="brand-logo left-align white-text">24 <fmt:message key="label.news" /></a>
            </div>
        </nav>
    </div>
    <div class="col s1 center-align" style="margin-top: 15px">
        <a href="#" data-target="slide-out" class="sidenav-trigger white-text"><i class="material-icons small">menu</i></a>
    </div>
</div>
</html>