<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>

<html lang="${sessionScope.language}">
<div class="row no-margin-cards">
    <c:choose>
        <c:when test="${requestScope.latestNews eq null}">
            No news.
        </c:when>
        <c:otherwise>
    <c:forEach var="news" items="${requestScope.latestNews}" varStatus="status">
        <c:if test="${status.index eq 0}">
            <div class="col s6">
                <a class="card featured" <c:if test="${sessionScope.user eq 'active'}"> href="controller?action=go_to_view_news&id=${news.id}" </c:if> target="_blank">
                <div class="card featured">
                    <div class="card-image">
                        <img src="${news.imagePath}" alt="">
                        <div class="card-title gradient">
                                ${news.title}
                            <div>${news.briefNews}</div>
                            <button class="btn transparent"><fmt:message key="label.learnMore" /></button>
                        </div>
                    </div>
                </div>
                </a>
            </div>
        </c:if>
        <c:if test="${status.index eq 1}">
            <div class="col s3">
                <a class="card" <c:if test="${sessionScope.user eq 'active'}"> href="controller?action=go_to_view_news&id=${news.id}" </c:if>>
                    <div class="card-image">
                        <img src="${news.imagePath}" alt="">
                        <div class="card-title flow-text gradient">
                                ${news.title}
                        </div>
                    </div>
                </a>
            </div>
        </c:if>
        <c:if test="${status.index eq 2}">
            <div class="col s3">
                <a class="card" <c:if test="${sessionScope.user eq 'active'}"> href="controller?action=go_to_view_news&id=${news.id}" </c:if>>
                    <div class="card-image">
                        <img src="${news.imagePath}" alt="">
                        <div class="card-title flow-text gradient">
                                ${news.title}
                        </div>
                    </div>
                </a>
            </div>
        </c:if>
        <c:if test="${status.index eq 3}">
            <div class="col s3">
                <a class="card" <c:if test="${sessionScope.user eq 'active'}"> href="controller?action=go_to_view_news&id=${news.id}" </c:if>>
                    <div class="card-image">
                        <img src="${news.imagePath}" alt="">
                        <div class="card-title flow-text gradient">
                                ${news.title}
                        </div>
                    </div>
                </a>
            </div>
        </c:if>
        <c:if test="${status.index eq 4}">
            <div class="col s3">
                <a class="card" <c:if test="${sessionScope.user eq 'active'}"> href="controller?action=go_to_view_news&id=${news.id}" </c:if>>
                    <div class="card-image">
                        <img src="${news.imagePath}" alt="">
                        <div class="card-title flow-text gradient">
                                ${news.title}
                        </div>
                    </div>
                </a>
            </div>
        </c:if>
    </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
</html>