<%@ include file="/WEB-INF/pages/tiles/body/localMessages.jsp" %>

<!DOCTYPE html>

<html lang="${sessionScope.language}">

<div class="container">
    <br><br>
    <div class="row">
        <div class="card grey-text">
            <div class="card-content">
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
                <div class="input-field col s6">
                    <input type="text" class="datepicker validate" required id="textarea3" name="date">
                    <label for="textarea3">${date}</label>
                </div>
                <div class="input-field col s6">
                </div>
                <div class="input-field col s12">
                    <textarea id="textarea5" class="materialize-textarea" name="content"></textarea>
                    <label for="textarea5">${content}</label>
                </div>
                <br>
                <div class="input-field col s12">
                    <input type="text" name="image" id="imageUrl" class="validate" required>
                    <label for="imageUrl">${addImage}</label>
                    <br><br>
                    <img id="preview" src="">
                </div>
                <c:if test="${not (param.error eq null)}">
                    <p class="h6 center-align" style="color:red;"><fmt:message bundle="${loc}"
                                                                               key="${param.error}"/></p>
                </c:if>
                <div class="row">
                    <div class="col s2" style="display: grid; grid-template-columns: repeat(1, 1fr); grid-gap:10px">
                        <button class="btn waves-effect grey lighten-1" type="submit" name="action"
                                value="add_news">${save}</button>
                    </div>
                </div>
            </form>
            </div>
            <br>
        </div>
    </div>
</div>
</html>