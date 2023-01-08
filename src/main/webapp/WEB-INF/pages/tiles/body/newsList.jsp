<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="text"/>
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
                <br>
                <span class="card-title left-align">${news.title}</span>
                <div class="card-content left-align"><p>${news.briefNews}</p></div>
                <span><i class="fab fa-google"></i><p class="left-align">${news.newsDate}</p></span>
                <c:if test="${(sessionScope.user eq 'active')}">
                    <div class="card-action">
                        <div class="row">
                            <div class="col s2">
                                <a class="grey-text-text" href="controller?action=go_to_view_news&id=${news.id}">
                                    <fmt:message key="label.moreInfo" /></a>
                            </div>
                            <div class="col s2 offset-s8">
                                <c:if test="${sessionScope.role eq 'admin'}">
                                <p>
                                    <label>
                                        <input type="checkbox" class="filled-in" name="Delete ${news.id}" value="${news.id}"/>
                                        <span><fmt:message key="label.delete" /></span>
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
                <fmt:message key="label.noNews" />
            </c:if>
        </div>
        <c:if test="${sessionScope.role eq 'admin'}">
            <div class="row">
                <div class="col s2 offset-s10">
                    <a class="waves-effect grey lighten-1 btn modal-trigger white-text" href="#modal1"><fmt:message key="label.delete" /></a>
                    <div id="modal1" class="modal">
                        <div class="modal-content">
                            <h4><fmt:message key="label.confirmation" /></h4>
                            <p><fmt:message key="label.sure" /></p>
                        </div>
                        <div class="modal-footer">
                            <a href="#!" class="modal-close waves-effect waves-red btn-flat"><fmt:message key="label.disagree" /></a>
                            <button class="btn-flat modal-close waves-effect waves-green" type="submit"
                                    name="action"
                                    value="delete_news"><fmt:message key="label.agree" />
                            </button>
                </div>
            </div>
        </c:if>
    </form>
</div>
</html>