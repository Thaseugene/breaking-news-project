<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>

<html lang="${sessionScope.language}">
<div class="container">
    <br><br>
    <div class="row">
        <div class="card grey-text">
            <form action="controller" method="post">
                <div class="input-field col s6">
                    <textarea id="textarea1" class="materialize-textarea" name="title">${news.title}</textarea>
                    <label for="textarea1"><fmt:message key="label.title"/></label>
                </div>
                <div class="input-field col s6">
                    <textarea id="textarea2" class="materialize-textarea" name="briefNews">${news.briefNews}</textarea>
                    <label for="textarea2"><fmt:message key="label.brief"/></label>
                </div>
                <div class="input-field col s12">
                    <textarea id="textarea3" class="materialize-textarea" name="content">${news.content}</textarea>
                    <label for="textarea3"><fmt:message key="label.content"/></label>
                </div>
                <input type="hidden" name="id" value="${news.id}"/>
                <p class="h6 center-align" style="color:red;">${error}</p>
                <button class="btn waves-effect grey lighten-1" type="submit" name="action" value="save_news">
                    <fmt:message key="label.save"/>
                </button>
                <button class="btn waves-effect grey lighten-1" type="submit" name="action" value="go_to_view_news">
                    <fmt:message key="label.cansel"/>
                </button>
            </form>
            <br>
        </div>
    </div>
</div>
</html>