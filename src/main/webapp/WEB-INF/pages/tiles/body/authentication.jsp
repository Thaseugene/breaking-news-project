<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>

<!DOCTYPE html>

<html lang="${sessionScope.language}">
<div class="container grey-text">
    <div class="row">
        <form class="col s6 offset-s3 card" action="controller" method="post">
            <br>
            <div class="row">
                <h5 class="center-align"><fmt:message key="label.loginTitle"/></h5>
                <br>

                <div class="input-field col s12">
                    <input id="Login" type="text" class="validate" required name="login">
                    <label for="Login"><fmt:message key="label.login"/></label>
                </div>
                <div class="input-field col s12">
                    <input id="Password" type="password" class="validate" required name="password">
                    <label for="Password"><fmt:message key="label.password"/></label>
                </div>
                <div class="input-field col s12">
                    <p class="h6 center-align" style="color:red;">${error}</p>
                    <button class="btn-large waves-effect grey lighten-1" type="submit" name="action" value="do_sign_in"
                            style="width: 60%">
                        <fmt:message key="label.login"/>
                    </button>
                    <div class="input-field col s12 center-align">
                        <a href="controller?action=go_to_reg_page" class="Not-a-Member"><fmt:message
                                key="label.notAMember"/></a>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</html>