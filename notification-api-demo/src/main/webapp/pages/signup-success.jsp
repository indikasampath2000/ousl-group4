<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>

<head>
    <title>Sign-up</title>

    <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
    <META HTTP-EQUIV="REFRESH"  CONTENT="15;URL=<c:url value="/welcome.html"/>">
</head>


<!-- Start Container -->
<div id="container">

    <!-- Start content area 1 -->
    <div class="content-area-signup">
        <div style="text-align: center; margin-top: 100px;">
            <div class="reg-text-style3" >
                Registration Successfully Completed.
            </div>
            <br/><br/>
            <div class="log-text-style2">
                You will receive an email to <b>${user.email}</b> very shortly from our team. Please follow the instructions to enable your account.
            </div>
        </div>
    </div>
    <!-- End content area 1 -->

    <div class="clear"></div>

</div>

<!-- End Container -->

<div class="clear"></div>