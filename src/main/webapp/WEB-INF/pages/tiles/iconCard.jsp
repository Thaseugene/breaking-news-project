
<%@ include file="/WEB-INF/pages/tiles/body/localMessages.jsp" %>
<!DOCTYPE html>

<html lang="${sessionScope.language}">
<div class="container  center-align">
    <div class="row">
        <div class="col s2">
            <img src="images/logo.jpg" alt="img" width="80%" height="auto">
        </div>
        <div class="col s2 offset-s8" style="margin-top: 25px">
            <label>${language}</label>
            <form action="controller" method="post">
                <select id="language" class="browser-default"  name="language" onchange="submit()">
                    <option value="en_US" <c:if test="${sessionScope.language eq 'en_US'}">selected</c:if>>${english}</option>
                    <option value="ru_RU" <c:if test="${sessionScope.language eq 'ru_RU'}">selected</c:if>>${russian}</option>
                </select>
                <input hidden name="action" value="change_lang">
            </form>
        </div>
    </div>
</div>
</html>