
<%@ include file="/WEB-INF/pages/tiles/body/localMessages.jsp" %>
<!DOCTYPE html>

<html lang="${sessionScope.language}">

<div class="container">
    <div class="row">
        <div class="col l6 s12">
            <h5 class="white-text">24 ${newsLabel}</h5>
        </div>
        <div class="col l4 offset-l2 s12">
            <h5 class="white-text">${links}</h5>
            <ul>
                <li><a class="grey-text text-lighten-3" href="#!">${link} 1</a></li>
                <li><a class="grey-text text-lighten-3" href="#!">${link} 2</a></li>
                <li><a class="grey-text text-lighten-3" href="#!">${link} 3</a></li>
                <li><a class="grey-text text-lighten-3" href="#!">${link} 4</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="footer-copyright">
    <div class="container">
        © 2023 24 ${newsLabel}
        <a class="grey-text text-lighten-4 right" href="#!">${moreLinks}</a>
    </div>
</div>
</html>