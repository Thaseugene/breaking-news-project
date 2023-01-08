<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>

<html lang="${sessionScope.language}">
<div class="nav-wrapper grey lighten-3">
    <div class="container">
        <div class="col s12">
				<span id="desktop-breadcrumbs" class="hide-on-med-and-down">
                    <c:choose>
                        <c:when test="${sessionScope.user eq 'active'}">
                            <a href="controller?action=go_to_news_list" class="breadcrumb grey-text"><i class="material-icons">home</i></a>
                            <c:choose>
                                <c:when test="${requestScope.presentation eq 'viewNews'}">
                                    <a href="controller?action=go_to_view_news&id=${news.id}" class="breadcrumb grey-text"><fmt:message key="label.viewNews" /></a>
                                </c:when>
                                <c:when test="${requestScope.presentation eq 'authentication'}">
                                    <a href="controller?action=go_to_auth_page" class="breadcrumb grey-text"><fmt:message key="label.auth" /></a>
                                </c:when>
                                <c:when test="${requestScope.presentation eq 'editNews'}">
                                    <a href="controller?action=go_to_view_news&id=${news.id}" class="breadcrumb grey-text"><fmt:message key="label.viewNews" /></a>
                                    <a href="controller?action=do_edit&id=${news.id}" class="breadcrumb grey-text"><fmt:message key="label.editNews" /></a>
                                </c:when>
                                <c:when test="${requestScope.presentation eq 'registration'}">
                                    <a href="controller?action=go_to_reg_page" class="breadcrumb grey-text"><fmt:message key="label.registration" /></a>
                                </c:when>
                                <c:when test="${requestScope.presentation eq 'addNews'}">
                            <a href="controller?action=go_to_add_news_page"
                               class="breadcrumb grey-text"><fmt:message key="label.addNews" /></a>
                                </c:when>
                            </c:choose>
                        </c:when>
                        <c:when test="${not (sessionScope.user eq 'active')}">
                            <a href="controller?action=go_to_news_list" class="breadcrumb grey-text"><i class="material-icons">home</i></a>
                            <c:choose>
                                <c:when test="${requestScope.presentation eq 'authentication'}">
                                    <a href="controller?action=go_to_auth_page" class="breadcrumb grey-text"><fmt:message key="label.auth" /></a>
                                </c:when>
                                <c:when test="${requestScope.presentation eq 'registration'}">
                                    <a href="controller?action=go_to_reg_page" class="breadcrumb grey-text"><fmt:message key="label.registration" /></a>
                                </c:when>
                            </c:choose>
                        </c:when>

                        <c:otherwise>
                            <a href="controller?action=go_to_news_list" class="breadcrumb grey-text"><i class="material-icons">home</i></a>
                        </c:otherwise>
                    </c:choose>
				</span>
        </div>
    </div>
</div>
</html>
