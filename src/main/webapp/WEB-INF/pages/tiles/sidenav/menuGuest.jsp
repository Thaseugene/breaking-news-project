
<%@ include file="/WEB-INF/pages/tiles/body/localMessages.jsp" %>
<!DOCTYPE html>

<html lang="${sessionScope.language}">
<li>
    <div class="user-view center-align">
        <div class="background">
            <img src="">
        </div>

        <a href="#user"><img style="border-radius: 50%; width: 100px; height: auto" src="images/anon.jpg"></a>
        <a href="#name" class="center-align"><span class="grey-text name">${guest}</span></a>
        <a href="#email" class="center-align"><span class="grey-text email">-</span></a>
    </div>
</li>
<li>
    <div class="divider"></div>
</li>
<li style="margin-top: 2px;">
    <a class="waves-effect grey lighten-1 white-text center-align" href="controller?action=go_to_auth_page">${signIn}</a>
</li>
<li style="margin-top: 2px;">
    <a class="waves-effect grey lighten-1 white-text center-align" href="controller?action=go_to_reg_page">${register}</a>
</li>
</html>