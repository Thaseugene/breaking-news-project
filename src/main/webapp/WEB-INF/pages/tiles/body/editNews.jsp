<%@ include file="/WEB-INF/pages/tiles/body/localMessages.jsp" %>
<!DOCTYPE html>

<html lang="${sessionScope.language}">

<div class="container">
    <br><br>
    <div class="row">
        <div class="card grey-text">

            <div class="card-content">
                <form action="controller" method="post">
                    <div class="input-field col s6">
                        <textarea id="textarea1" class="materialize-textarea" name="title">${news.title}</textarea>
                        <label for="textarea1">${title}</label>
                    </div>
                    <div class="input-field col s6">
                        <textarea id="textarea2" class="materialize-textarea"
                                  name="briefNews">${news.briefNews}</textarea>
                        <label for="textarea2">${brief}</label>
                    </div>
                    <div class="input-field col s6">
                        <input type="text" class="datepicker" id="textarea3" name="date"
                               value="<fmt:formatDate value="${news.publicationDate}" type="date" pattern="yyyy/MM/dd"/>"
                               .trim()">
                        <label for="textarea3">${date}</label>
                    </div>
                    <div class="input-field col s6">
                        <input type="text" class="timepicker" id="textarea4" name="time"
                               value="<fmt:formatDate value="${news.publicationDate}" type="date" pattern="HH:mm a"/>" .trim()">
                        <label for="textarea4">${time}</label>
                    </div>
                    <div class="input-field col s12">
                        <textarea id="textarea5" class="materialize-textarea" name="content">${news.content}</textarea>
                        <label for="textarea5">${content}</label>
                    </div>
                    <input type="hidden" name="id" value="${news.id}"/>
                    <c:if test="${not (param.error eq null)}">
                        <p class="h6 center-align" style="color:red;"><fmt:message bundle="${loc}"
                                                                                   key="${param.error}"/></p>
                    </c:if>


                    <div class="row">
                        <div class="col s4" style="display: grid; grid-template-columns: repeat(2, 1fr); grid-gap:10px">
                            <button id="saveBtn" class="btn waves-effect grey lighten-1" type="submit" name="action"
                                    value="save_news">${save}</button>

                            <button id="cancelBtn" class="btn waves-effect grey lighten-1" type="submit" name="action"
                                    value="go_to_view_news">${cansel}</button>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
</html>