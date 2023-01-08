<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>

<html lang="${sessionScope.language}">

<c:if test="${requestScope.presentation eq 'news'}">
    <c:import url="/WEB-INF/pages/tiles/body/newsList.jsp" />
</c:if>

<c:if test="${requestScope.presentation eq 'viewNews'}">
    <c:import url="/WEB-INF/pages/tiles/body/viewNews.jsp" />
</c:if>

<c:if test="${requestScope.presentation eq 'editNews'}">
    <c:import url="/WEB-INF/pages/tiles/body/editNews.jsp" />
</c:if>

<c:if test="${requestScope.presentation eq 'addNews'}">
    <c:import url="/WEB-INF/pages/tiles/body/addNews.jsp" />
</c:if>

<c:if test="${requestScope.presentation eq 'registration'}">
    <c:import url="/WEB-INF/pages/tiles/body/registration.jsp" />
</c:if>
</html>