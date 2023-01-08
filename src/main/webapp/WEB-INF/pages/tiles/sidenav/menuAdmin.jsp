<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>

<html lang="${sessionScope.language}">
<li>
    <div class="user-view center-align">
        <div class="background">
            <img src="">
        </div>
        <a href="#user"><img style="border-radius: 50%; width: 100px; height: auto" src="images/anon.jpg"></a>
        <a href="#name" class="center-align"><span class="grey-text name">${sessionScope.name} ${sessionScope.surname}</span></a>
        <a href="#email" class="center-align"><span class="grey-text email">${sessionScope.email}</span></a>
    </div>
</li>
<li>
    <div class="divider"></div>
</li>

<li style="margin-top: 2px;">
    <a class="waves-effect grey lighten-1 white-text center-align" href="controller?action=go_to_news_list"><fmt:message key="label.viewAllNews" /></a>
</li>
<li style="margin-top: 2px;">
    <a class="waves-effect grey lighten-1 white-text center-align" href="controller?action=go_to_add_news_page"><fmt:message key="label.addNews" /></a>
</li>
<li style="margin-top: 2px;">
    <a class="waves-effect grey lighten-1 white-text center-align" href="controller?action=do_sign_out"><fmt:message key="label.signOut" /></a>
</li>
</html>
