<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>

<html lang="${sessionScope.language}">
<div class="container grey-text">
    <div class="row">
        <form class="col s8 offset-s2 card" action="controller" method="post">
            <div class="row">
                <h5 class="center-align"><fmt:message key="label.createNew" /></h5>
                <p class="h6 center-align" style="color:lightseagreen;">${output}</p>
                <div class="input-field col s6">
                    <input id="Name" type="text" class="validate" required name="name">
                    <label for="Name"><fmt:message key="label.name" /></label>
                </div>
                <div class="input-field col s6">
                    <input id="Surname" type="text" class="validate" required name="surname">
                    <label for="Surname"><fmt:message key="label.surname" /></label>
                </div>
                <div class="input-field col s12">
                    <input id="Email" type="email" class="validate" required name="email">
                    <label for="Email"><fmt:message key="label.email" /></label>
                </div>
                <div class="input-field col s12">
                    <input id="Login" type="text" class="validate" required name="login">
                    <label for="Login"><fmt:message key="label.login" /></label>
                </div>
                <div class="input-field col s6">
                    <input id="Password" type="password" class="validate" required name="password">
                    <label for="Name"><fmt:message key="label.password" /></label>
                </div>
                <div class="input-field col s6">
                    <input id="Confirm password" type="password" class="validate" required name="confirmPassword">
                    <label for="Confirm password"><fmt:message key="label.confirmPass" /></label>
                </div>
                <div class="input-field col s12">
                    <p class="h6 center-align" style="color:red;">${error}</p>
                    <button class="btn-large waves-effect grey lighten-1" type="submit" name="action" value="do_registration" style="width: 65%">
                        <fmt:message key="label.create" /></button>
                </div>
            </div>
        </form>
    </div>
</div>
</html>
