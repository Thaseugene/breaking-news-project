<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>

<html lang="${sessionScope.language}">
<c:if test="${sessionScope.user eq 'active'}">
    <c:if test="${sessionScope.role eq 'user'}">
        <c:import url="/WEB-INF/pages/tiles/sidenav/menuUser.jsp"/>
    </c:if>
    <c:if test="${sessionScope.role eq 'admin'}">
        <c:import url="/WEB-INF/pages/tiles/sidenav/menuAdmin.jsp"/>
    </c:if>
</c:if>
<c:if test="${not (sessionScope.user eq 'active')}">
    <c:import url="/WEB-INF/pages/tiles/sidenav/menuGuest.jsp"/>
</c:if>
</html>