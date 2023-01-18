
<%@ include file="/WEB-INF/pages/tiles/body/localMessages.jsp" %>

<!DOCTYPE html>

<html lang="${sessionScope.language}">
<div class="container grey-text">
    <div class="row">
        <form class="col s6 offset-s3 card" action="controller" method="post">
            <br>
            <div class="row">
                <h5 class="center-align">${t_login}</h5>
                <br>

                <div class="input-field col s12">
                    <input id="Login" type="text" class="validate" required name="login">
                    <label for="Login">${login}</label>
                </div>
                <div class="input-field col s12">
                    <input id="Password" type="password" class="validate" required name="password">
                    <label for="Password">${password}</label>
                </div>
                <div class="input-field col s12">
                    <c:if test="${not (param.error eq null)}">
                        <p class="h6 center-align" style="color:red;"><fmt:message bundle="${loc}"
                                                                                   key="${param.error}"/></p>
                    </c:if>
                    <button class="btn-large waves-effect grey lighten-1" type="submit" name="action" value="do_sign_in"
                            style="width: 60%">
                        ${login}
                    </button>
                    <div class="input-field col s12 center-align">
                        <a href="controller?action=go_to_reg_page" class="Not-a-Member">${notAMember}</a>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</html>