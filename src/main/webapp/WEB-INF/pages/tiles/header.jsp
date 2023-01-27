
<%@ include file="/WEB-INF/pages/tiles/body/localMessages.jsp" %>
<!DOCTYPE html>

<html lang="${sessionScope.language}">
<div class="row black">
    <div class="col s11">
        <nav class="z-depth-0">
            <div class="nav-wrapper black">
                <a href="controller?action=go_to_news_list" class="brand-logo left-align white-text">24 ${newsLabel}</a>
                <div class="col s2 offset-s6 white-text center-align" id="clock"></div>
            </div>
        </nav>
    </div>
    <div class="col s1 center-align" style="margin-top: 15px">
        <a href="#" data-target="slide-out" class="sidenav-trigger white-text"><i class="material-icons small">menu</i></a>
    </div>
</div>
</html>