<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>

<html lang="${sessionScope.language}">
<c:choose>
    <c:when test="${requestScope.presentation eq 'registration'}">
        <c:import url="/WEB-INF/pages/tiles/body/registration.jsp" />
    </c:when>
    <c:when test="${requestScope.presentation eq 'authentication'}">
        <c:import url="/WEB-INF/pages/tiles/body/authentication.jsp" />
    </c:when>
    <c:otherwise>
            <c:import url="/WEB-INF/pages/tiles/body/guestInfo.jsp"/>
    </c:otherwise>
</c:choose>
</html>