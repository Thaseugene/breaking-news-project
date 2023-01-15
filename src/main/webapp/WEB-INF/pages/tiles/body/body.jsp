
<%@ include file="/WEB-INF/pages/tiles/body/localMessages.jsp" %>
<!DOCTYPE html>

<html lang="${sessionScope.language}">
<c:choose>
    <c:when test="${requestScope.presentation eq 'viewNews'}">
        <c:import url="/WEB-INF/pages/tiles/body/viewNews.jsp"/>
    </c:when>
    <c:when test="${requestScope.presentation eq 'editNews'}">
        <c:import url="/WEB-INF/pages/tiles/body/editNews.jsp"/>
    </c:when>
    <c:when test="${requestScope.presentation eq 'addNews'}">
        <c:import url="/WEB-INF/pages/tiles/body/addNews.jsp"/>
    </c:when>
    <c:when test="${requestScope.presentation eq 'authentication'}">
        <c:import url="/WEB-INF/pages/tiles/body/authentication.jsp"/>
    </c:when>
    <c:when test="${requestScope.presentation eq 'registration'}">
        <c:import url="/WEB-INF/pages/tiles/body/registration.jsp"/>
    </c:when>
    <c:otherwise>
        <c:import url="/WEB-INF/pages/tiles/body/newsList.jsp"/>
    </c:otherwise>
</c:choose>
</html>