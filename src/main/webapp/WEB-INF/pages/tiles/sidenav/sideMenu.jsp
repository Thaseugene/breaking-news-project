
<%@ include file="/WEB-INF/pages/tiles/body/localMessages.jsp" %>
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