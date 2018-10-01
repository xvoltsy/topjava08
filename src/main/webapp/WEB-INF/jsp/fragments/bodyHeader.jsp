<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">

    <div class="container">
        <div class="navbar-header navbar-brand"><spring:message code="app.title"/></div>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="nav navbar-nav navbar-right">

                <li class="nav-item form-inline">
                    <form:form class="navbar-form " action="logout" method="post">

                        <sec:authorize access="isAuthenticated()">
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a class="btn btn-info" href="users"><spring:message code="users.title"/></a>
                            </sec:authorize>
                            <button class="btn btn-primary" type="submit">
                                <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                            </button>
                        </sec:authorize>
                    </form:form>
                </li>
                <li class="nav-item dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">en<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">en</a></li>
                        <li><a href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">ru</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>