
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="localization.local" var="loc"/>

<fmt:message bundle="${loc}" key="label.title" var="title"/>
<fmt:message bundle="${loc}" key="label.brief" var="brief"/>
<fmt:message bundle="${loc}" key="label.content" var="content"/>
<fmt:message bundle="${loc}" key="label.save" var="save"/>
<fmt:message bundle="${loc}" key="label.addImage" var="addImage"/>
<fmt:message bundle="${loc}" key="label.login" var="login"/>
<fmt:message bundle="${loc}" key="label.loginTitle" var="t_login"/>
<fmt:message bundle="${loc}" key="label.password" var="password"/>
<fmt:message bundle="${loc}" key="label.name" var="name"/>
<fmt:message bundle="${loc}" key="label.surname" var="surname"/>
<fmt:message bundle="${loc}" key="label.email" var="email"/>
<fmt:message bundle="${loc}" key="label.confirmPass" var="confirmPassword"/>
<fmt:message bundle="${loc}" key="label.create" var="create"/>
<fmt:message bundle="${loc}" key="label.viewAllNews" var="viewAll"/>
<fmt:message bundle="${loc}" key="label.addNews" var="addNews"/>
<fmt:message bundle="${loc}" key="label.signOut" var="signOut"/>
<fmt:message bundle="${loc}" key="label.signIn" var="signIn"/>
<fmt:message bundle="${loc}" key="label.register" var="register"/>
<fmt:message bundle="${loc}" key="label.news" var="newsLabel"/>
<fmt:message bundle="${loc}" key="label.edit" var="edit"/>
<fmt:message bundle="${loc}" key="label.delete" var="delete"/>
<fmt:message bundle="${loc}" key="label.confirmation" var="confirmation"/>
<fmt:message bundle="${loc}" key="label.sure" var="sure"/>
<fmt:message bundle="${loc}" key="label.agree" var="agree"/>
<fmt:message bundle="${loc}" key="label.disagree" var="disagree"/>
<fmt:message bundle="${loc}" key="label.learnMore" var="learnMore"/>
<fmt:message bundle="${loc}" key="label.moreInfo" var="moreInfo"/>
<fmt:message bundle="${loc}" key="label.noNews" var="noNews"/>
<fmt:message bundle="${loc}" key="label.cansel" var="cansel"/>
<fmt:message bundle="${loc}" key="label.notAMember" var="notAMember"/>
<fmt:message bundle="${loc}" key="label.createNew" var="createNew"/>
<fmt:message bundle="${loc}" key="label.language" var="language"/>
<fmt:message bundle="${loc}" key="label.guest" var="guest"/>
<fmt:message bundle="${loc}" key="label.viewNews" var="viewNews"/>
<fmt:message bundle="${loc}" key="label.auth" var="authentication"/>
<fmt:message bundle="${loc}" key="label.editNews" var="editNews"/>
<fmt:message bundle="${loc}" key="label.link" var="link"/>
<fmt:message bundle="${loc}" key="label.links" var="links"/>
<fmt:message bundle="${loc}" key="label.moreLinks" var="moreLinks"/>
<fmt:message bundle="${loc}" key="label.registration" var="registration"/>
<fmt:message bundle="${loc}" key="label.ru" var="russian"/>
<fmt:message bundle="${loc}" key="label.en" var="english"/>
<fmt:message bundle="${loc}" key="label.date" var="date"/>
<fmt:message bundle="${loc}" key="label.time" var="time"/>
<fmt:message bundle="${loc}" key="label.loginPattern" var="loginPattern"/>
<fmt:message bundle="${loc}" key="label.passwordPattern" var="passwordPattern"/>
<fmt:message bundle="${loc}" key="label.right" var="right"/>
<fmt:message bundle="${loc}" key="label.wrong" var="wrong"/>
<fmt:message bundle="${loc}" key="label.newsCount" var="newsCount"/>
