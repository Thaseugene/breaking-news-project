<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>

<html lang="${sessionScope.language}">

<c:import url="/WEB-INF/pages/tiles/body/photoGrid.jsp"/>

<br>
<div class="container grey-text">
    <c:forEach var="news" items="${requestScope.news}">
        <!--card with curve and border-->
        <article class="card-news border curve">
            <div>
                <img src="https://images.unsplash.com/photo-1477414348463-c0eb7f1359b6?ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80"
                     alt="image">
            </div>
            <div>
                <span class="card-title">${news.title}</span>
                <div class="card-content left-align">
                    <p>${news.briefNews}</p>
                </div>
                <span>
          <i class="fab fa-google"></i>
            <p class="left-align">${news.newsDate}</p>
         </span>
                <c:if test="${(sessionScope.user eq 'active')}">
                    <div class="card-action">
                        <a class="right-align" href="controller?action=go_to_view_news&id=${news.id}"><fmt:message
                                key="label.moreInfo"/></a>
                    </div>
                </c:if>
            </div>
        </article>
    </c:forEach>

    <div>
        <c:if test="${requestScope.news eq null}">
            <fmt:message key="label.noNews"/>
        </c:if>
    </div>
</div>

</html>
