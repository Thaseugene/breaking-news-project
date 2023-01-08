<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>

<html lang="${sessionScope.language}">
<div class="container  center-align">
    <div class="row">
        <div class="col s2">
            <img src="images/logo.jpg" alt="img" width="80%" height="auto">
        </div>
        <div class="col s2 offset-s8" style="margin-top: 25px">
            <label><fmt:message key="label.language" /></label>
            <form action="controller" method="post">
                <select id="language" class="browser-default"  name="language" onchange="submit()">
                    <option value="en_US" <c:if test="${sessionScope.language eq 'en_US'}">selected</c:if>>English</option>
                    <option value="ru_RU" <c:if test="${sessionScope.language eq 'ru_RU'}">selected</c:if>>Русский</option>
                </select>
                <c:if test="${requestScope.presentation eq 'viewNews'}">
                    <input hidden name="id" value="${requestScope.news.id}">
                </c:if>

                <c:if test="${requestScope.presentation eq 'editNews'}">
                    <input hidden name="id" value="${requestScope.news.id}">
                </c:if>
                <input hidden name="action" value="${param.get("action")}">
            </form>
        </div>
    </div>
</div>
</html>