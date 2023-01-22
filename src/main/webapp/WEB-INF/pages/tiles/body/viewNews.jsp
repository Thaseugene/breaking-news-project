<%@ include file="/WEB-INF/pages/tiles/body/localMessages.jsp" %>
<!DOCTYPE html>

<html lang="${sessionScope.language}">
<div class="container grey-text">
    <br>
    <div class="row">
        <div class="col s10 offset-s1">
            <div class="card">
                <c:if test="${not (param.saveMessage eq null)}">
                    <div class="hiddendiv">
                    <a onclick="M.toast({html: '<fmt:message bundle="${loc}" key="${param.saveMessage}"/>',
                            classes: 'cyan lighten-2'})" class="btn" id="clickButton"></a>
                    </div>
                </c:if>
                <div class="card-image">
                    <img src="${news.imagePath}"
                         alt="image">
                </div>
                <div class="card-content grey-text">
                    <h4 class="card-title left-align" id="1">${news.title}</h4>
                    <p style="text-align: justify">${news.content}</p>
                    <c:if test="${sessionScope.role eq 'admin'}">
                        <form action="controller" method="post">
                            <input type="hidden" name="Delete ${news.id}" value="${news.id}"/>
                            <a class="waves-effect grey lighten-1 btn white-text"
                               href="controller?action=go_to_edit_page&id=${news.id}">
                                    ${edit}</a>
                            <a class="waves-effect grey lighten-1 btn modal-trigger white-text"
                               href="#modal1">${delete}</a>
                            <div id="modal1" class="modal">
                                <div class="modal-content">
                                    <h4>${confirmation}</h4>
                                    <p>${sure}</p>
                                </div>
                                <div class="modal-footer">
                                    <a href="#!" class="modal-close waves-effect waves-red btn-flat">${disagree}</a>
                                    <button class="btn-flat modal-close waves-effect waves-green" type="submit"
                                            name="action"
                                            value="delete_news">${agree}</button>
                                </div>
                            </div>
                        </form>
                    </c:if>
                </div>
                <br>
            </div>
        </div>
    </div>
</div>
</html>