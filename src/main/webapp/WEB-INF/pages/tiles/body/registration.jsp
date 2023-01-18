<%@ include file="/WEB-INF/pages/tiles/body/localMessages.jsp" %>
<!DOCTYPE html>

<html lang="${sessionScope.language}">
<div class="container grey-text">
    <div class="row">
        <form class="col s8 offset-s2 card" action="controller" method="post">
            <div class="row">
                <h5 class="center-align">${createNew}</h5>
                <c:if test="${not (param.output eq null)}">
                    <p class="h6 center-align" style="color:lightseagreen;"><fmt:message bundle="${loc}"
                                                                                         key="${param.output}"/></p>
                </c:if>
                <div class="input-field col s6">
                    <input id="Name" type="text" class="validate" required name="name">
                    <label for="Name">${name}</label>
                </div>
                <div class="input-field col s6">
                    <input id="Surname" type="text" class="validate" required name="surname">
                    <label for="Surname">${surname}</label>
                </div>
                <div class="input-field col s12">
                    <input id="Email" type="email" class="validate" required name="email">
                    <label for="Email">${email}</label>
                </div>
                <div class="input-field col s12">
                    <input id="Login" type="text" class="validate" required name="login">
                    <label for="Login">${login}</label>
                </div>
                <div class="input-field col s6">
                    <input id="Password" type="password" class="validate" required name="password">
                    <label for="Name">${password}</label>
                </div>
                <div class="input-field col s6">
                    <input id="Confirm password" type="password" class="validate" required name="confirmPassword">
                    <label for="Confirm password">${confirmPassword}</label>
                </div>
                <div class="input-field col s12">
                    <c:if test="${not (param.error eq null)}">
                        <p class="h6 center-align" style="color:red;"><fmt:message bundle="${loc}"
                                                                                   key="${param.error}"/></p>
                    </c:if>
                    <button class="btn-large waves-effect grey lighten-1" type="submit" name="action"
                            value="do_registration" style="width: 65%">
                        ${create}</button>
                </div>
            </div>
        </form>
    </div>
</div>
</html>
