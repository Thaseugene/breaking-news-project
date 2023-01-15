
<%@ include file="/WEB-INF/pages/tiles/body/localMessages.jsp" %>
<!DOCTYPE html>

<html lang="${sessionScope.language}">
<c:import url="/WEB-INF/pages/tiles/body/photoGrid.jsp" />

<br>
<div class="container grey-text">
    <form action="controller" method="post">
    <c:forEach var="news" items="${requestScope.news}">
        <article class="card-news border curve">
            <div><img src="${news.imagePath}" alt="image"></div>
            <div>
                <c:if test="${(sessionScope.user eq 'active')}"><br></c:if>
                <span class="card-title">${news.title}</span>
                <div class="card-content left-align"><p>${news.briefNews}</p></div>
                <span><i class="fab fa-google"></i><p class="left-align">${news.newsDate}</p></span>
                <c:if test="${(sessionScope.user eq 'active')}">
                    <div class="card-action">
                        <div class="row">
                            <div class="col s2">
                                <a class="grey-text-text" href="controller?action=go_to_view_news&id=${news.id}">${moreInfo}</a>
                            </div>
                            <div class="col s2 offset-s8">
                                <c:if test="${sessionScope.role eq 'admin'}">
                                <p>
                                    <label>
                                        <input type="checkbox" class="filled-in" name="Delete ${news.id}" value="${news.id}"/>
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
    </form>
</div>
</html>