<%@ include file="/WEB-INF/pages/tiles/body/localMessages.jsp" %>
<!DOCTYPE html>

<html lang="${sessionScope.language}">
<c:import url="/WEB-INF/pages/tiles/body/photoGrid.jsp"/>

<br>
<c:if test="${not (param.addMessage eq null)}">
    <div class="hiddendiv">
        <a onclick="M.toast({html: '<fmt:message bundle="${loc}" key="${param.addMessage}"/>',
                classes: 'cyan lighten-2'})" class="btn" id="clickButton"></a>
    </div>
</c:if>
<div class="container grey-text">
    <form action="controller" method="post">
        <c:forEach var="news" items="${requestScope.news}">
            <article class="card-news border curve">
                <div><img src="${news.imagePath}" alt="image"></div>
                <div>
                    <c:if test="${(sessionScope.user eq 'active')}"><br></c:if>
                    <span class="card-title">${news.title}</span>
                    <div class="card-content left-align"><p>${news.briefNews}</p></div>
                    <span><i class="fab fa-google"></i><p class="left-align">
                        <c:if test="${not empty news.publicationDate}">
                            <i class="tiny material-icons">access_time</i>
                            <fmt:formatDate value="${news.publicationDate}" type="date"
                                            pattern="EEEE dd MMMM yyyy"/>
                        </c:if>
                </p></span>
                    <c:if test="${(sessionScope.user eq 'active')}">
                        <div class="card-action">
                            <div class="row">
                                <div class="col s2">
                                    <a class="grey-text-text"
                                       href="controller?action=go_to_view_news&id=${news.id}">${moreInfo}</a>
                                </div>
                                <div class="col s2 offset-s8">
                                    <c:if test="${sessionScope.role eq 'admin'}">
                                        <p>
                                            <label>
                                                <input type="checkbox" class="filled-in" name="Delete ${news.id}"
                                                       value="${news.id}"/>
                                                <span>${delete}</span>
                                            </label>
                                        </p>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </article>
        </c:forEach>
        <div>
            <c:if test="${requestScope.news eq null}">
                ${noNews}
            </c:if>
        </div>
        <c:if test="${sessionScope.role eq 'admin'}">
        <div class="row">
            <div class="col s2 offset-s10">
                <a class="waves-effect grey lighten-1 btn modal-trigger white-text" href="#modal1">${delete}</a>
                <div id="modal1" class="modal">
                    <div class="modal-content">
                        <h4>${confirmation}</h4>
                        <p>${sure}</p>
                    </div>
                    <div class="modal-footer">
                        <a href="#!" class="modal-close waves-effect waves-red btn-flat">${disagree}</a>
                        <button class="btn-flat modal-close waves-effect waves-green" type="submit"
                                name="action"
                                value="delete_news">${agree}
                        </button>
                    </div>
                </div>
                </c:if>
            </div>
        </div>
    </form>
</div>
<div class="container">
    <div class="row">
        <div class="col s3 offset-s4 center-align">
            <ul class="pagination">
                <c:if test="${requestScope.numOfPages != 1}">
                <c:if test="${requestScope.currentPage != 1}">
                <li class="disabled"><a
                        href="controller?action=go_to_news_list&currentPage=${requestScope.currentPage - 1}&countOfNewsOnPage=${requestScope.countOfNewsOnPage}"><i
                        class="material-icons">chevron_left</i></a></li>
                </c:if>
                <c:forEach var="i" begin="1" end="${requestScope.numOfPages}">
                <c:choose>
                <c:when test="${requestScope.currentPage eq i}">
                <li class="active"><a href="#!">${requestScope.currentPage}</a></li>
                </c:when>
                <c:otherwise>
                <li class="waves-effect"><a
                        href="controller?action=go_to_news_list&currentPage=${i}&countOfNewsOnPage=${requestScope.countOfNewsOnPage}">${i}</a>
                </li>
                </c:otherwise>
                </c:choose>
                </c:forEach>
                <c:if test="${requestScope.currentPage != requestScope.numOfPages}">
                <li class="waves-effect"><a
                        href="controller?action=go_to_news_list&currentPage=${requestScope.currentPage + 1}&countOfNewsOnPage=${requestScope.countOfNewsOnPage}"><i
                        class="material-icons">chevron_right</i></a></li>
                </c:if>
                </c:if>
        </div>
        <div class="col s2" style="margin-top: 5px">
            <form action="controller" method="post">
                <select id="countOfNewsOnPage" class="browser-default" name="countOfNewsOnPage" onchange="submit()">
                    <option value="5" <c:if test="${requestScope.countOfNewsOnPage eq 5}">selected</c:if>>
                        5 ${newsCount}</option>
                    <option value="10" <c:if test="${requestScope.countOfNewsOnPage eq 10}">selected</c:if>>
                        10 ${newsCount}</option>
                </select>
                <input hidden name="currentPage" value="1">
                <input hidden name="action" value="go_to_news_list">
            </form>
        </div>
        </ul>
    </div>
</div>
</html>