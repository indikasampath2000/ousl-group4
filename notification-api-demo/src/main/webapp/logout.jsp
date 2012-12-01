<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>
<%@ page import="javax.servlet.http.Cookie" %>

<%
    if (request.getSession(false) != null) {
        session.invalidate();
    }
    Cookie terminate = new Cookie(TokenBasedRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, null);
    String contextPath = request.getContextPath();
    terminate.setPath(contextPath != null && contextPath.length() > 0 ? contextPath : "/");
    terminate.setMaxAge(0);
    response.addCookie(terminate);

    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevent caching at the proxy server

%>

<c:redirect url="/goodbye.html"/>