
<%@ include file="/WEB-INF/pages/tiles/body/localMessages.jsp" %>

<!DOCTYPE html>

<html lang="${sessionScope.language}">
<div class="container">
    <br><br>
    <div class="row">
        <div class="card grey-text">
            <form action="controller" method="post" enctype="multipart/form-data">
                <div class="input-field col s6">
                <textarea id="textarea1" type="text" class="materialize-textarea validate" required
                          name="title"></textarea>
                    <label for="textarea1">${title}</label>
                </div>
                <div class="input-field col s6">
                <textarea id="textarea2" type="text" class="materialize-textarea validate" required
                          name="briefNews"></textarea>
                    <label for="textarea2">${brief}</label>
                </div>
                <div class="input-field col s12">
                    <textarea id="textarea3" class="materialize-textarea validate" required name="content"></textarea>
                    <label for="textarea3">${content}</label>
                </div>
                <br>
                <div class="input-field col s12">
                    <div class="file-field input-field">
                        <div class="btn-small waves-effect grey lighten-1">
                            <span>${addImage}</span>
                            <input type="file" name="image" class="validate" required>
                        </div>
                        <div class="file-path-wrapper">
                            <input class="file-path validate" type="text">
                        </div>
                    </div>
                </div>
                <c:if test="${not (param.error eq null)}">
                <p class="h6 center-align" style="color:red;"><fmt:message bundle="${loc}"
                                                                           key="${param.error}"/></p>
                </c:if>
                <button class="btn waves-effect grey lighten-1" type="submit" name="action" value="add_news">${save}</button>
            </form>
            <br>
        </div>
    </div>
</div>
</html>